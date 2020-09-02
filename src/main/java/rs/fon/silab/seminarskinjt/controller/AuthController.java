/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.silab.seminarskinjt.dto.RegisterUserDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.exception.LoginException;
import rs.fon.silab.seminarskinjt.service.AuthService;
import rs.fon.silab.seminarskinjt.validator.UserValidator;

/**
 *
 * @author Bozidar
 */
@Controller
public class AuthController {

    private final AuthService authService;
    private final UserValidator userValidator;
    private final MessageSource messageSource;

    @Autowired
    public AuthController(
            AuthService authService,
            UserValidator userValidator,
            MessageSource messageSource) {
        this.authService = authService;
        this.userValidator = userValidator;
        this.messageSource = messageSource;
    }

    @GetMapping("login")
    public String showLogin(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");
        if (user == null) {
            return "login";
        }
        return "redirect:/home";
    }

    @PostMapping("login")
    public String login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        if (email.isEmpty() || password.isEmpty()) {
            String message = messageSource.getMessage("userDto.login.fields.empty", null, locale);
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/login";
        }
        try {
            UserDto userDto = authService.login(email, password);
            request.getSession(true).setAttribute("user", userDto);
            return "redirect:/home";
        } catch (LoginException ex) {
            String message = messageSource.getMessage("userDto.login.failed", null, locale);
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/login";
        }
    }

    @GetMapping("register")
    public String showRegister(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");
        if (user == null) {
            return "register";
        }
        return "redirect:/home";
    }

    @PostMapping("register")
    public String register(
            @ModelAttribute @Validated RegisterUserDto registerUserDto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        if (result.hasErrors()) {
            return "register";
        }
        authService.register(registerUserDto);
        String message = messageSource.getMessage("registerUserDto.register.success", null, locale);
        redirectAttributes.addFlashAttribute("success", message);

        return "redirect:/login";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).removeAttribute("user");
        return "redirect:/login";
    }

    @ModelAttribute(name = "registerUserDto")
    private RegisterUserDto getRegisterUserDto() {
        return new RegisterUserDto();
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

}

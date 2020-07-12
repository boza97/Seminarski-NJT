/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import rs.fon.silab.seminarskinjt.entity.User;
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
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(
            AuthService authService,
            ModelMapper modelMapper,
            UserValidator userValidator) {
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.userValidator = userValidator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
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
            RedirectAttributes redirectAttributes) {
        if (email.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Morate popuniti sva polja");
            return "redirect:/login";
        }
        try {
            User user = authService.login(email, password);
            UserDto userDto = convertToDto(user);
            request.getSession(true).setAttribute("user", userDto);
            return "redirect:/home";
        } catch (LoginException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
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
            @ModelAttribute(name = "userDto") @Validated RegisterUserDto userDto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "register";
        }
        User user = convertToEntity(userDto);
        authService.register(user);
        redirectAttributes.addFlashAttribute("success", "Uspešno ste se registrovali, prijavite se kako bi nastavili dalje.");
        return "redirect:/login";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).removeAttribute("user");
        return "redirect:/login";
    }

    @ModelAttribute(name = "userDto")
    private RegisterUserDto getUserDto() {
        return new RegisterUserDto();
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(RegisterUserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

}

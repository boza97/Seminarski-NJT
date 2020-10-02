/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rs.fon.silab.seminarskinjt.dto.RegisterUserDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.service.UserService;

/**
 *
 * @author Bozidar
 */
@Component
public class UserValidator implements Validator {

    private final UserService authService;

    @Autowired
    public UserValidator(UserService authService) {
        super();
        this.authService = authService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return RegisterUserDto.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterUserDto user = (RegisterUserDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "registerUserDto.firstname", "default");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "registerUserDto.lastname", "default");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "registerUserDto.email.empty", "default");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "registerUserDto.password.empty", "default");

        if (errors.hasErrors()) {
            return;
        }

        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", "registerUserDto.email", "default");
            return;
        }

        UserDto dbUser = authService.findByEmail(user.getEmail());
        if (dbUser != null) {
            errors.rejectValue("email", "registerUserDto.exists", "default");
            return;
        }

        regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-\\.]).{6,}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(user.getPassword());
        if (!matcher.matches()) {
            errors.rejectValue("password", "registerUserDto.password", "default");
            return;
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "registerUserDto.confirmPassword", "default");
        }
    }

}

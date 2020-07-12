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
import rs.fon.silab.seminarskinjt.entity.User;
import rs.fon.silab.seminarskinjt.repository.UserRepository;

/**
 *
 * @author Bozidar
 */
@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> type) {
        return RegisterUserDto.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterUserDto user = (RegisterUserDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", null, "Ime ne sme biti prazno.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", null, "Prezime ne sme biti prazno.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", null, "E-mail adresa ne sme biti prazna.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "Lozinka ne sme biti prazna.");

        if (errors.hasErrors()) {
            return;
        }

        User entity = userRepository.findByEmail(user.getEmail());
        if (entity != null) {
            errors.rejectValue("email", null, "Korisnik već postoji sa zadatom email adresom.");
            return;
        }

        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", null, "E-mail adresa nije validna.");
            return;
        }

        regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-\\.]).{6,}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(user.getPassword());
        if (!matcher.matches()) {
            errors.rejectValue("password", null, "Lozinka mora imati minimum 6 karaktera, sadržati barem jedno veliko slovo,"
                    + "broj i specijalni karakter.");
            return;
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", null, "Lozinke se ne poklapaju.");
        }
    }

}

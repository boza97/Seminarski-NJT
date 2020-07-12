/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rs.fon.silab.seminarskinjt.dto.OrderDto;

/**
 *
 * @author Bozidar
 */
@Component
public class OrderValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return OrderDto.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactName", null, "Ime i prezime su obavezni.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", null, "Grad je obavezan.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", null, "Adresa je obavezna.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", null, "Telefon je obavezan.");
    }
    
}

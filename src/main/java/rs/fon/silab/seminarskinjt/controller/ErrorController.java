/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Bozidar
 */
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        System.out.println("====================================================================");
        System.out.println("@ControllerAdvice exception ocured: Exception===========");
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        System.out.println("====================================================================");

        return "error";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service;

import java.util.Locale;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bozidar
 */
public interface CartService {
    boolean add(Long productId, StringBuilder message, HttpSession session, Locale locale);
    boolean remove(Long productId, StringBuilder message, HttpSession session, Locale locale);
}

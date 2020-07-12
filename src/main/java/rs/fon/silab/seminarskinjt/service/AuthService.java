/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service;

import rs.fon.silab.seminarskinjt.entity.User;
import rs.fon.silab.seminarskinjt.exception.LoginException;

/**
 *
 * @author Bozidar
 */
public interface AuthService {
    void register(User user);
    User findByEmail(String email);
    User login(String email, String password) throws LoginException;
}

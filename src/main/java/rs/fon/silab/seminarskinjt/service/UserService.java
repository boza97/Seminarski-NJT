/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service;

import rs.fon.silab.seminarskinjt.dto.RegisterUserDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.exception.LoginException;

/**
 *
 * @author Bozidar
 */
public interface UserService {
    void register(RegisterUserDto user);
    UserDto findByEmail(String email);
    UserDto login(String email, String password) throws LoginException;
}

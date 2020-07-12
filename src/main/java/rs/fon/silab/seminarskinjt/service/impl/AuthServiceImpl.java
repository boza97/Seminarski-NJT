/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.seminarskinjt.entity.User;
import rs.fon.silab.seminarskinjt.exception.LoginException;
import rs.fon.silab.seminarskinjt.repository.UserRepository;
import rs.fon.silab.seminarskinjt.service.AuthService;

/**
 *
 * @author Bozidar
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User login(String email, String password) throws LoginException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new LoginException("Podaci koje ste uneli se ne poklapaju.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginException("Podaci koje ste uneli se ne poklapaju.");
        }
        return user;
    }

}

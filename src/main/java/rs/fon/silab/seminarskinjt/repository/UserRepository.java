/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository;

import rs.fon.silab.seminarskinjt.entity.UserEntity;

/**
 *
 * @author Bozidar
 */
public interface UserRepository {
    void save(UserEntity user);
    UserEntity findByEmail(String email);
}

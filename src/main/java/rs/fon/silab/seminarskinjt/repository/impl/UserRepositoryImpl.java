/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.stereotype.Component;
import rs.fon.silab.seminarskinjt.entity.UserEntity;
import rs.fon.silab.seminarskinjt.repository.UserRepository;

/**
 *
 * @author Bozidar
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    private final EntityManagerFactory emf;

    public UserRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("GenerisanjeBazePU");
    }

    @Override
    public void save(UserEntity user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();
        em.persist(user);
        entityTransaction.commit();

        em.close();
    }

    @Override
    public UserEntity findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            UserEntity entity = (UserEntity) em.createQuery("SELECT u FROM UserEntity u WHERE u.email = '" + email + "'").getSingleResult();
            em.close();
            return entity;
        } catch (Exception ex) {
            em.close();
            return null;
        }
    }
}

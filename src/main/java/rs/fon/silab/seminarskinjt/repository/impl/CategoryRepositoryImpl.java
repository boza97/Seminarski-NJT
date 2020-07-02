/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Component;
import rs.fon.silab.seminarskinjt.entity.CategoryEntity;
import rs.fon.silab.seminarskinjt.repository.CategoryRepository;

/**
 *
 * @author Bozidar
 */
@Component
public class CategoryRepositoryImpl implements CategoryRepository {

    private final EntityManagerFactory emf;

    public CategoryRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("GenerisanjeBazePU");;
    }

    @Override
    public List<CategoryEntity> getAll() {
        EntityManager em = emf.createEntityManager();
        List<CategoryEntity> entities = em.createQuery("SELECT c FROM CategoryEntity c").getResultList();
        em.close();
        return entities;
    }

}

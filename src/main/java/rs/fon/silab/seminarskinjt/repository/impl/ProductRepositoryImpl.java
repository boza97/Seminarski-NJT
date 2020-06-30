/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.stereotype.Component;
import rs.fon.silab.seminarskinjt.entity.ProductEntity;
import rs.fon.silab.seminarskinjt.repository.ProductRepository;

/**
 *
 * @author Bozidar
 */
@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManagerFactory emf;

    public ProductRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("GenerisanjeBazePU");
    }

    @Override
    public void save(ProductEntity product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();
        em.persist(product);
        entityTransaction.commit();

        em.close();
    }

    @Override
    public List<ProductEntity> getFeatured() {
        EntityManager em = emf.createEntityManager();
        List<ProductEntity> entities = em.createQuery("SELECT p FROM ProductEntity p WHERE p.featured = 1").getResultList();
        em.close();
        return entities;
    }

    @Override
    public ProductEntity findById(Long id) {
        EntityManager em = emf.createEntityManager();
        ProductEntity entity = em.find(ProductEntity.class, id);
        em.close();
        return entity;
    }

}

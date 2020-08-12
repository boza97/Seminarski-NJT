/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.fon.silab.seminarskinjt.entity.Product;

/**
 *
 * @author Bozidar
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p WHERE p.featured = 1")
    List<Product> findAllFeatured();
    
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    List<Product> findAllByCategory(Long categoryId);
    
}

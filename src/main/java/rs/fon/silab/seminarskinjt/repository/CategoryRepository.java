/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.fon.silab.seminarskinjt.entity.Category;

/**
 *
 * @author Bozidar
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}

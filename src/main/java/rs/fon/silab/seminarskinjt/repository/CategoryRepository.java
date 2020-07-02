/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.repository;

import java.util.List;
import rs.fon.silab.seminarskinjt.entity.CategoryEntity;

/**
 *
 * @author Bozidar
 */
public interface CategoryRepository {
    List<CategoryEntity> getAll();
}

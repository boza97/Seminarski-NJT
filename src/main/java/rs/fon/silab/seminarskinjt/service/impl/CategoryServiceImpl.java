/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.fon.silab.seminarskinjt.entity.CategoryEntity;
import rs.fon.silab.seminarskinjt.repository.CategoryRepository;
import rs.fon.silab.seminarskinjt.service.CategoryService;

/**
 *
 * @author Bozidar
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.getAll();
    }
}

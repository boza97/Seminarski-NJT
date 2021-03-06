/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.seminarskinjt.dto.CategoryDto;
import rs.fon.silab.seminarskinjt.entity.Category;
import rs.fon.silab.seminarskinjt.repository.CategoryRepository;
import rs.fon.silab.seminarskinjt.service.CategoryService;

/**
 *
 * @author Bozidar
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream()
                .map(c -> modelMapper.map(c, CategoryDto.class))
                .collect(Collectors.toList());
    }
}

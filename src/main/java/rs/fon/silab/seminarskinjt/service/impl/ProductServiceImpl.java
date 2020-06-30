/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.fon.silab.seminarskinjt.entity.ProductEntity;
import rs.fon.silab.seminarskinjt.repository.ProductRepository;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(ProductEntity product) {
        this.productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getFeatured() {
        return this.productRepository.getFeatured();
    }

    @Override
    public ProductEntity findById(Long id) {
        return this.productRepository.findById(id);
    }

}

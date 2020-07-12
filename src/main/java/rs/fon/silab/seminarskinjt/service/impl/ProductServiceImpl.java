/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.seminarskinjt.entity.Product;
import rs.fon.silab.seminarskinjt.repository.ProductRepository;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public List<Product> getFeatured() {
        return this.productRepository.findAllFeatured();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> getAllByCategory(Long id) {
        return this.productRepository.findAllByCategory(id);
    }

}

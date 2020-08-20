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
import rs.fon.silab.seminarskinjt.dto.ProductDto;
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
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public List<ProductDto> getFeatured() {
        List<Product> featuredProducts = this.productRepository.findAllFeatured();
        return featuredProducts.stream()
                .map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product != null) {
            return modelMapper.map(product, ProductDto.class);
        }
        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = this.productRepository.findAll();
        return products.stream()
                .map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllByCategory(Long id) {
        List<Product> products = this.productRepository.findAllByCategory(id);
        return products.stream()
                .map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
    }

}

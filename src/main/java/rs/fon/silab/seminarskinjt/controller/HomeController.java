/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.entity.ProductEntity;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Controller
public class HomeController {
    
    private final ProductService productService;
    private final ModelMapper modelMapper;
    
    @Autowired
    public HomeController(
            ProductService productService,
            ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping(path = {"", "home"})
    public String index() {
        return "home";
    }
    
    @ModelAttribute(name = "featuredProducts")
    private List<ProductDto> getFeaturedProducs() {
        List<ProductEntity> featuredProducts = productService.getFeatured();
        return featuredProducts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private ProductDto convertToDto(ProductEntity product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }
}

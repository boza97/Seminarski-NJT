/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.entity.ProductEntity;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductsController(
            ProductService productServic,
            ModelMapper modelMapper) {
        this.productService = productServic;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{productId}/view")
    public String view(
            @PathVariable("productId") Long productId,
            Model model) {
        ProductEntity product = productService.findById(productId);
        model.addAttribute("productDto", convertToDto(product));
        return "products/view";
    }

    private ProductDto convertToDto(ProductEntity product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

}

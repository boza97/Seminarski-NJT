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
import rs.fon.silab.seminarskinjt.dto.IDto;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.entity.Product;
import rs.fon.silab.seminarskinjt.service.ProductService;
import rs.fon.silab.seminarskinjt.util.DtoUtil;

/**
 *
 * @author Bozidar
 */
@Controller
public class HomeController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final DtoUtil dtoUtil;

    @Autowired
    public HomeController(
            ProductService productService,
            ModelMapper modelMapper,
            DtoUtil dtoUtil) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.dtoUtil = dtoUtil;
    }

    @GetMapping(path = {"", "home"})
    public String index() {
        return "home";
    }

    @ModelAttribute(name = "featuredProducts")
    private List<IDto> getFeaturedProducs() {
        List<Product> featuredProducts = productService.getFeatured();
        return featuredProducts.stream()
                .map(p -> dtoUtil.convertToDto(p, new ProductDto()))
                .collect(Collectors.toList());
    }
}

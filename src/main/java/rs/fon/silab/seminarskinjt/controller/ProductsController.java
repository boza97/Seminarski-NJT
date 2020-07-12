/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rs.fon.silab.seminarskinjt.dto.CategoryDto;
import rs.fon.silab.seminarskinjt.dto.IDto;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.entity.Category;
import rs.fon.silab.seminarskinjt.entity.Product;
import rs.fon.silab.seminarskinjt.service.CategoryService;
import rs.fon.silab.seminarskinjt.service.ProductService;
import rs.fon.silab.seminarskinjt.util.DtoUtil;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final DtoUtil dtoUtil;

    @Autowired
    public ProductsController(
            ProductService productServic,
            CategoryService categoryService,
            DtoUtil dtoUtil) {
        this.productService = productServic;
        this.categoryService = categoryService;
        this.dtoUtil = dtoUtil;
    }

    @GetMapping
    public String index() {
        return "products";
    }

    @GetMapping("{productId}/view")
    public String view(
            @PathVariable("productId") Long productId,
            Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("productDto", dtoUtil.convertToDto(product, new ProductDto()));
        return "products/view";
    }

    @GetMapping("category/{id}")
    public ModelAndView productsByCategory(@PathVariable("id") String id) {
        List<Product> entities = new ArrayList<>();
        if (id.equals("*")) {
            entities = productService.getAll();
        } else {
            Long categoryId = Long.parseLong(id);
            entities = productService.getAllByCategory(categoryId);
        }
        List<IDto> products = entities.stream().map(p -> dtoUtil.convertToDto(p, new ProductDto())).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products/productsByCategory");
        modelAndView.addObject("products", products);

        return modelAndView;
    }

    @ModelAttribute("products")
    private List<IDto> getProducts() {
        List<Product> products = productService.getAll();
        return products.stream()
                .map(p -> dtoUtil.convertToDto(p, new ProductDto()))
                .collect(Collectors.toList());
    }

    @ModelAttribute("categories")
    private List<IDto> getCategories() {
        List<Category> categories = categoryService.getAll();
        return categories.stream()
                .map(c -> dtoUtil.convertToDto(c, new CategoryDto()))
                .collect(Collectors.toList());
    }

}

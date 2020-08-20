/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rs.fon.silab.seminarskinjt.dto.CategoryDto;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.service.CategoryService;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(
            ProductService productServic,
            CategoryService categoryService) {
        this.productService = productServic;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index() {
        return "products";
    }

    @GetMapping("{productId}/view")
    public String view(
            @PathVariable("productId") Long productId,
            Model model) {
        
        ProductDto productDto = productService.findById(productId);
        model.addAttribute("productDto", productDto);
        return "products/view";
    }

    @GetMapping("category/{id}")
    public ModelAndView productsByCategory(@PathVariable("id") String id) {
        
        List<ProductDto> products = new ArrayList<>();
        if (id.equals("*")) {
            products = productService.getAll();
        } else {
            Long categoryId = Long.parseLong(id);
            products = productService.getAllByCategory(categoryId);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products/productsByCategory");
        modelAndView.addObject("products", products);

        return modelAndView;
    }

    @ModelAttribute("products")
    private List<ProductDto> getProducts() {
        return this.productService.getAll();
    }

    @ModelAttribute("categories")
    private List<CategoryDto> getCategories() {
        return this.categoryService.getAll();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.dto.request.CartRequestDataDto;
import rs.fon.silab.seminarskinjt.dto.response.ResponseDataDto;
import rs.fon.silab.seminarskinjt.entity.ProductEntity;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    
    private final ProductService productService;
    private final ModelMapper modelMapper;
    
    @Autowired
    public CartController(
            ProductService productService,
            ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }
    
    @ResponseBody
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDataDto> add(
            @RequestBody CartRequestDataDto requestData,
            HttpServletRequest request) {
        
        ResponseDataDto responseData = new ResponseDataDto();
        ProductEntity product = productService.findById(requestData.getProductId());
        if (product != null) {
            ProductDto productDto = convertToDto(product);
            if (!exists(request.getSession(true), productDto)) {
                addToCart(request.getSession(true), productDto);
                responseData.setMessage("Uspešno ste dodali proizvod u korpu.");
            } else {
                responseData.setMessage("Proizvod je već dodat u korpu.");
            }
        } else {
            responseData.setMessage("Proizvod ne postoji sa zadatim id-em.");
            responseData.setStatus("ERROR");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    
    private ProductDto convertToDto(ProductEntity product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }
    
    private boolean exists(HttpSession session, ProductDto product) {
        List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
        if (cart != null) {
            for (ProductDto productDto : cart) {
                if (productDto.equals(product)) {
                    return true;
                }
            }
            
        }
        return false;
    }
    
    private void addToCart(HttpSession session, ProductDto productDto) {
        List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(productDto);
        session.setAttribute("cart", cart);
        System.out.println("************KORPA*************");
        System.out.println(cart.size());
    }
    
}

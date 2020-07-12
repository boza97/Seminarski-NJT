/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.dto.request.CartRequestDataDto;
import rs.fon.silab.seminarskinjt.dto.response.ResponseDataDto;
import rs.fon.silab.seminarskinjt.entity.Product;
import rs.fon.silab.seminarskinjt.service.ProductService;
import rs.fon.silab.seminarskinjt.util.DtoUtil;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final DtoUtil dtoUtil;

    @Autowired
    public CartController(
            ProductService productService,
            DtoUtil dtoUtil) {
        this.productService = productService;
        this.dtoUtil = dtoUtil;
    }

    @GetMapping
    public String index() {
        return "cart";
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDataDto> add(
            @RequestBody CartRequestDataDto requestData,
            HttpServletRequest request) {

        ResponseDataDto responseData = new ResponseDataDto();
        Product product = productService.findById(requestData.getProductId());
        if (product != null) {
            ProductDto productDto = (ProductDto) dtoUtil.convertToDto(product, new ProductDto());
            if (!exists(request.getSession(), productDto)) {
                addToCart(request.getSession(), productDto);
                responseData.setMessage("Uspešno ste dodali proizvod u korpu.");
            } else {
                responseData.setMessage("Proizvod je već dodat u korpu.");
            }
        } else {
            responseData.setMessage("Proizvod ne postoji sa zadatim id-em.");
            responseData.setCode("ERROR");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping(
            value = "{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDataDto> remove(
            @PathVariable("productId") Long productId,
            HttpSession session) {
        System.out.println(productId);
        ResponseDataDto responseData = new ResponseDataDto("Proizvod je obrisan iz korpe", null);
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            responseData.setCode("EMPTY_CART");
            responseData.setMessage("Vaša korpa je već prazna.");
        } else {
            cart = cart.stream()
                    .filter(oi -> !Objects.equals(oi.getProduct().getId(), productId))
                    .collect(Collectors.toList());
            session.setAttribute("cart", cart);

            if (cart.isEmpty()) {
                responseData.setCode("EMPTY_CART");
                responseData.setMessage("Vaša korpa je prazna.");
            }
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    private boolean exists(HttpSession session, ProductDto product) {
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");
        if (cart != null) {
            for (OrderItemDto orderItemDto : cart) {
                if (orderItemDto.getProduct().equals(product)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addToCart(HttpSession session, ProductDto productDto) {
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        OrderItemDto newOrderItem = new OrderItemDto();
        newOrderItem.setAmount(productDto.getPrice());
        newOrderItem.setProduct(productDto);
        newOrderItem.setQuantity(1);
        cart.add(newOrderItem);
        session.setAttribute("cart", cart);
    }

}

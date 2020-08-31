/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.dto.request.CartRequestDataDto;
import rs.fon.silab.seminarskinjt.dto.response.ResponseDataDto;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @Autowired
    public CartController(
            ProductService productService,
            MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
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
            HttpServletRequest request,
            Locale locale) {

        ResponseDataDto responseData = new ResponseDataDto();
        ProductDto productDto = productService.findById(requestData.getProductId());
        String message = "";

        if (productDto != null) {
            if (!exists(request.getSession(), productDto)) {

                addToCart(request.getSession(), productDto);
                message = messageSource.getMessage("label.cart.add.success", null, locale);
                responseData.setMessage(message);
            } else {
                message = messageSource.getMessage("label.cart.product.exists", null, locale);
                responseData.setMessage(message);
            }
        } else {

            message = messageSource.getMessage("label.product.not.exist", null, locale);
            responseData.setMessage(message);
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
            HttpSession session,
            Locale locale) {
        
        String message = messageSource.getMessage("label.cart.remove.success", null, locale);
        ResponseDataDto responseData = new ResponseDataDto(message, null);
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {

            message = messageSource.getMessage("label.cart.empty", null, locale);
            responseData.setCode("EMPTY_CART");
            responseData.setMessage(message);
            
        } else {
            cart = cart.stream()
                    .filter(oi -> !Objects.equals(oi.getProduct().getId(), productId))
                    .collect(Collectors.toList());
            session.setAttribute("cart", cart);

            if (cart.isEmpty()) {
                message = messageSource.getMessage("label.cart.empty", null, locale);
                responseData.setCode("EMPTY_CART");
                responseData.setMessage(message);
            }
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("add-quantities")
    public String addQuantities(
            @RequestParam(value = "productid[]") Long[] ids,
            @RequestParam(value = "quantity[]") int[] quantities,
            HttpSession session) {
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "cart";
        }

        addQuantities(cart, ids, quantities);

        return "redirect:/orders/checkout";
    }

    private void addQuantities(List<OrderItemDto> cart, Long[] ids, int[] quantities) {
        for (int i = 0; i < cart.size(); i++) {
            OrderItemDto orderItem = cart.get(i);
            if (Objects.equals(orderItem.getProduct().getId(), ids[i])) {
                orderItem.setQuantity(quantities[i]);
                orderItem.setAmount(orderItem.getProduct().getPrice().multiply(new BigDecimal(quantities[i])));
            }
        }
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

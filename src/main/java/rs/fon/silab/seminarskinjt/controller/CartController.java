/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.request.CartRequestDataDto;
import rs.fon.silab.seminarskinjt.dto.response.ResponseDataDto;
import rs.fon.silab.seminarskinjt.service.CartService;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
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
            HttpSession session,
            Locale locale) {

        ResponseDataDto responseData = new ResponseDataDto();
        StringBuilder message = new StringBuilder();

        boolean flag = cartService.add(requestData.getProductId(), message, session, locale);
        responseData.setMessage(message.toString());

        if (!flag) {
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

        StringBuilder message = new StringBuilder();
        ResponseDataDto responseData = new ResponseDataDto();

        boolean flag = cartService.remove(productId, message, session, locale);
        responseData.setMessage(message.toString());

        if (!flag) {
            responseData.setCode("EMPTY_CART");
        } else {
            responseData.setCode("OK");
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

}

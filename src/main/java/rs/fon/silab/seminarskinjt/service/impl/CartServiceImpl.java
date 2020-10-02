/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.ProductDto;
import rs.fon.silab.seminarskinjt.service.CartService;
import rs.fon.silab.seminarskinjt.service.ProductService;

/**
 *
 * @author Bozidar
 */
@Service
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private final MessageSource messageSource;

    @Autowired
    public CartServiceImpl(
            ProductService productService,
            MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
    }

    @Override
    public boolean add(Long productId, StringBuilder message, HttpSession session, Locale locale) {

        ProductDto productDto = productService.findById(productId);

        if (productDto == null) {
            message.append(messageSource.getMessage("label.product.not.exist", null, locale));
            return false;
        }

        if (exists(session, productDto)) {
            message.append(messageSource.getMessage("label.cart.product.exists", null, locale));
        } else {
            addToCart(session, productDto);
            message.append(messageSource.getMessage("label.cart.add.success", null, locale));
        }

        return true;
    }

    @Override
    public boolean remove(Long productId, StringBuilder message, HttpSession session, Locale locale) {
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");

        if (cart == null) {
            message.append(messageSource.getMessage("label.cart.empty", null, locale));
            return false;
        }

        cart = cart.stream()
                .filter(oi -> !Objects.equals(oi.getProduct().getId(), productId))
                .collect(Collectors.toList());
        session.setAttribute("cart", cart);

        if (cart.isEmpty()) {
            message.append(messageSource.getMessage("label.cart.empty", null, locale));
            return false;
        }
        
        message.append(messageSource.getMessage("label.cart.remove.success", null, locale));
        return true;
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

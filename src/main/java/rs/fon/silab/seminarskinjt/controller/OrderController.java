/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.silab.seminarskinjt.dto.OrderDto;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.service.OrderService;
import rs.fon.silab.seminarskinjt.validator.OrderValidator;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderValidator orderValidator;

    @Autowired
    public OrderController(
            OrderService orderService,
            OrderValidator orderValidator) {
        this.orderService = orderService;
        this.orderValidator = orderValidator;
    }

    @GetMapping
    private String showOrders() {
        return "orders";
    }

    @GetMapping("checkout")
    public String checkout(HttpSession session) {
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "cart";
        }

        return "checkout";
    }

    @PostMapping
    public String order(
            @ModelAttribute("orderDto") @Validated OrderDto orderDto,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDto", orderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDto", result);
            return "redirect:/orders/checkout";
        }

        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        UserDto userDto = (UserDto) session.getAttribute("user");
        orderDto.setItems(cart);
        orderDto.setUser(userDto);

        orderService.save(orderDto);

        redirectAttributes.addFlashAttribute("success", "Uspešno ste izvšili narudžbinu.");
        return "redirect:/orders";
    }

    @ModelAttribute("orderDto")
    private OrderDto getOrderDto() {
        return new OrderDto();
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(orderValidator);
    }

    @ModelAttribute("orders")
    private List<OrderDto> getOrders() {
        return orderService.getAll();
    }

}

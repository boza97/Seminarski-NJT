/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.silab.seminarskinjt.dto.OrderDto;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.entity.Order;
import rs.fon.silab.seminarskinjt.entity.OrderItem;
import rs.fon.silab.seminarskinjt.entity.User;
import rs.fon.silab.seminarskinjt.service.AuthService;
import rs.fon.silab.seminarskinjt.service.OrderService;
import rs.fon.silab.seminarskinjt.util.DtoUtil;
import rs.fon.silab.seminarskinjt.validator.OrderValidator;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;
    private final OrderValidator orderValidator;
    private final DtoUtil dtoUtil;

    @Autowired
    public OrderController(
            OrderService orderService,
            AuthService authService,
            OrderValidator orderValidator,
            DtoUtil dtoUtil) {
        this.orderService = orderService;
        this.authService = authService;
        this.dtoUtil = dtoUtil;
        this.orderValidator = orderValidator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(orderValidator);
    }

    @GetMapping
    private String showOrders() {
        return "orders";
    }

    @PostMapping("checkout")
    public String checkout(
            @RequestParam(value = "productid[]") Long[] ids,
            @RequestParam(value = "quantity[]") int[] quantities,
            HttpServletRequest request) {
        List<OrderItemDto> cart = (List<OrderItemDto>) request.getSession(true).getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "cart";
        }

        addQuantities(cart, ids, quantities);
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
        Order order = (Order) dtoUtil.convertToEntity(orderDto, new Order());
        List<OrderItem> items = cart.stream()
                .map(oi -> (OrderItem) dtoUtil.convertToEntity(oi, new OrderItem()))
                .collect(Collectors.toList());
        order.setUser(authService.findByEmail(userDto.getEmail()));
        order.setItems(items);
        order.caluclateTotal();

        orderService.save(order);

        redirectAttributes.addFlashAttribute("success", "Uspešno ste izvšili narudžbinu.");
        return "redirect:/orders";
    }

    @ModelAttribute("orderDto")
    private OrderDto getOrderDto() {
        return new OrderDto();
    }

    @ModelAttribute("orders")
    private List<OrderDto> getOrders() {
        List<Order> entities = orderService.getAll();
        return entities.stream()
                .map(o -> (OrderDto) dtoUtil.convertToDto(o, new OrderDto()))
                .collect(Collectors.toList());
    }

    private void addQuantities(List<OrderItemDto> cart, Long[] ids, int[] quantities) {
        for (int i = 0; i < cart.size(); i++) {
            OrderItemDto orderItem = cart.get(i);
            if (Objects.equals(orderItem.getProduct().getId(), ids[i])) {
                orderItem.setQuantity(quantities[i]);
                orderItem.setAmount(orderItem.getProduct().getPrice() * quantities[i]);
            }
        }
    }
}

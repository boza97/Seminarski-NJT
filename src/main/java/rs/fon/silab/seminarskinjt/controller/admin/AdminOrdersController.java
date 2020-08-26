/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.fon.silab.seminarskinjt.dto.OrderDto;
import rs.fon.silab.seminarskinjt.service.OrderService;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {

    private final OrderService orderService;

    @Autowired
    public AdminOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String index() {
        return "admin/orders";
    }

    @ModelAttribute(name = "orders")
    private List<OrderDto> getOrders() {
        return this.orderService.getAll();
    }

}

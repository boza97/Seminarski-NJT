/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.seminarskinjt.dto.OrderDto;
import rs.fon.silab.seminarskinjt.entity.Order;
import rs.fon.silab.seminarskinjt.entity.Product;
import rs.fon.silab.seminarskinjt.entity.User;
import rs.fon.silab.seminarskinjt.repository.OrderRepository;
import rs.fon.silab.seminarskinjt.repository.ProductRepository;
import rs.fon.silab.seminarskinjt.repository.UserRepository;
import rs.fon.silab.seminarskinjt.service.OrderService;

/**
 *
 * @author Bozidar
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void save(OrderDto orderDto) {

        Order order = modelMapper.map(orderDto, Order.class);
        order.getItems().forEach(i -> {
            Product product = productRepository.findById(i.getProduct().getId()).get();
            int newUnitsInStock = product.getQuantity() - i.getQuantity();

            i.setProduct(product);
            i.setOrder(order);
        });

        User user = userRepository.findById(order.getUser().getId()).get();
        order.setUser(user);
        order.caluclateTotal();

        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .collect(Collectors.toList());
    }

}

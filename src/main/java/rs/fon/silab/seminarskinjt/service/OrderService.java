/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service;

import java.util.List;
import rs.fon.silab.seminarskinjt.dto.OrderDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;

/**
 *
 * @author Bozidar
 */
public interface OrderService {
    void save(OrderDto orderDto);
    OrderDto findById(Long id);
    List<OrderDto> getAll();
    List<OrderDto> getAllByUser(UserDto userDto);
}

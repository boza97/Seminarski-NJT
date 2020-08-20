/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service;

import java.util.List;
import rs.fon.silab.seminarskinjt.dto.OrderDto;

/**
 *
 * @author Bozidar
 */
public interface OrderService {
    void save(OrderDto orderDto);
    List<OrderDto> getAll();
}

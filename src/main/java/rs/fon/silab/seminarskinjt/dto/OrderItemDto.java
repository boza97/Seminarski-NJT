/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Bozidar
 */
public class OrderItemDto {

    private OrderItemIdDto orderItemId;
    private ProductDto product;
    private int quantity;
    private BigDecimal amount;

    public OrderItemDto() {
    }

    public OrderItemDto(ProductDto product, int quantity, BigDecimal amount) {
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public OrderItemIdDto getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(OrderItemIdDto orderItemId) {
        this.orderItemId = orderItemId;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItemDto other = (OrderItemDto) obj;
        if (!Objects.equals(this.orderItemId, other.orderItemId)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

}

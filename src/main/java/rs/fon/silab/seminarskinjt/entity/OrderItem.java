/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Bozidar
 */
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem implements Serializable, IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ORDER_ITEM")
    @TableGenerator(name = "GEN_ORDER_ITEM", table = "GEN_ID",
            pkColumnName = "PK_GEN", valueColumnName = "VALUE_GEN",
            pkColumnValue = "TABLE_ORDER_ITEM", initialValue = 0, allocationSize = 1)
    private Long serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int quantity;

    @Column(columnDefinition = "DECIMAL", precision = 10, scale = 2)
    private double amount;

    public OrderItem() {
    }

    public OrderItem(Long serialNumber, Order order, Product product, int quantity, double amount) {
        this.serialNumber = serialNumber;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.serialNumber);
        hash = 89 * hash + this.quantity;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        return hash;
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
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" + "serialNumber=" + serialNumber + ", quantity=" + quantity + ", amount=" + amount + '}';
    }

}

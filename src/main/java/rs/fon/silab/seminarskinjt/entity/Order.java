/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Bozidar
 */
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ORDER")
    @TableGenerator(name = "GEN_ORDER", table = "GEN_ID",
            pkColumnName = "PK_GEN", valueColumnName = "VALUE_GEN",
            pkColumnValue = "TABLE_ORDER", initialValue = 0, allocationSize = 1)
    private Long id;
    @Column(name = "CONTACT_NAME")
    private String contactName;
    private String city;
    private String address;
    private String phone;
    private BigDecimal total;
    
    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
    
    public Order() {
        this.items = new ArrayList<>();
    }
    
    public Order(Long id, String contactName, String city, String address, String phone, BigDecimal total, Timestamp createdAt, User user, List<OrderItem> items) {
        this.id = id;
        this.contactName = contactName;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.createdAt = createdAt;
        this.user = user;
        this.items = items;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
        for (OrderItem item : items) {
            item.setOrder(this);
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.contactName);
        hash = 47 * hash + Objects.hashCode(this.city);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.phone);
        hash = 47 * hash + Objects.hashCode(this.total);
        hash = 47 * hash + Objects.hashCode(this.createdAt);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public void caluclateTotal() {
        this.total = new BigDecimal(0);
        for (OrderItem item : items) {
            this.total = this.total.add(item.getAmount());
        }
    }
    
    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Bozidar
 */
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable, IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_PRODUCT")
    @TableGenerator(name = "GEN_PRODUCT", table = "GEN_ID",
            pkColumnName = "PK_GEN", valueColumnName = "VALUE_GEN",
            pkColumnValue = "TABLE_PRODUCT", initialValue = 0, allocationSize = 1)
    private Long id;
    private String name;

    @Column(columnDefinition = "DECIMAL", precision = 10, scale = 2)
    private double price;

    @Column(columnDefinition = "TEXT")
    private String details;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    private String image;
    private int featured;
    private int quantity;

    public Product() {
    }

    public Product(Long id, String name, double price, String details, Category category, String image, int featured, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.category = category;
        this.image = image;
        this.featured = featured;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductEntity{" + "id=" + id + ", name=" + name + ", price=" + price + ", details=" + details + ", category=" + category + ", image=" + image + ", featured=" + featured + ", quantity=" + quantity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.details);
        hash = 37 * hash + Objects.hashCode(this.category);
        hash = 37 * hash + Objects.hashCode(this.image);
        hash = 37 * hash + this.featured;
        hash = 37 * hash + this.quantity;
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

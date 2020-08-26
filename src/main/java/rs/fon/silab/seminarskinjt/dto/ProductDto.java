/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Bozidar
 */
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Map<String, LocalizedProductDto> localizations = new HashMap<>();
    private CategoryDto category;
    private String image;
    private int featured;
    private int quantity;

    public ProductDto() {
    }

    public ProductDto(String name, BigDecimal price, CategoryDto category, String image, int featured, int quantity) {
        this.name = name;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<String, LocalizedProductDto> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(Map<String, LocalizedProductDto> localizations) {
        this.localizations = localizations;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
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
    public int hashCode() {
        int hash = 7;
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
        final ProductDto other = (ProductDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

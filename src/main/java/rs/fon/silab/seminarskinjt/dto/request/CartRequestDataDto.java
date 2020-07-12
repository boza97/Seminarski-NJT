/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.dto.request;

import rs.fon.silab.seminarskinjt.dto.IDto;

/**
 *
 * @author Bozidar
 */
public class CartRequestDataDto implements IDto{
    
    private Long productId;

    public CartRequestDataDto() {
    }

    public CartRequestDataDto(Long productId) {
        this.productId = productId;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
}

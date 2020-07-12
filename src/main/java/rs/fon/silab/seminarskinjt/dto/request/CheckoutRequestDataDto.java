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
public class CheckoutRequestDataDto implements IDto {

    private String[] productid;
    private String[] quantity;
    private String checkout;

    public CheckoutRequestDataDto() {
    }

    public CheckoutRequestDataDto(String[] productid, String[] quantity, String checkout) {
        this.productid = productid;
        this.quantity = quantity;
        this.checkout = checkout;
    }

    public String[] getProductid() {
        return productid;
    }

    public void setProductid(String[] productid) {
        this.productid = productid;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

}

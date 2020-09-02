/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.service;

import com.stripe.model.Charge;
import rs.fon.silab.seminarskinjt.dto.request.ChargeRequestDto;

/**
 *
 * @author Bozidar
 */
public interface StripeService {
    
    public Charge charge(ChargeRequestDto chargeRequest) throws Exception;
    
}

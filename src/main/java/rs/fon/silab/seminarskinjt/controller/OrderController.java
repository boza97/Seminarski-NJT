/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.controller;

import com.stripe.model.Charge;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.silab.seminarskinjt.dto.OrderDto;
import rs.fon.silab.seminarskinjt.dto.OrderItemDto;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.dto.request.ChargeRequestDto;
import rs.fon.silab.seminarskinjt.service.OrderService;
import rs.fon.silab.seminarskinjt.service.StripeService;
import rs.fon.silab.seminarskinjt.validator.OrderValidator;

/**
 *
 * @author Bozidar
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final StripeService stripeService;
    private final OrderValidator orderValidator;
    private final MessageSource messageSource;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    public OrderController(
            OrderService orderService,
            StripeService stripeService,
            OrderValidator orderValidator,
            MessageSource messageSource) {
        this.orderService = orderService;
        this.orderValidator = orderValidator;
        this.messageSource = messageSource;
        this.stripeService = stripeService;
    }

    @GetMapping
    private String showOrders(
            HttpSession session,
            Model model) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        List<OrderDto> orders = orderService.getAllByUser(userDto);
        model.addAttribute("orders", orders);

        return "orders";
    }

    @GetMapping("checkout")
    public String checkout(
            HttpSession session,
            Model model) {
        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "cart";
        }

        int amount = getTotal(cart).intValueExact();
        model.addAttribute("amount", amount * 100);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequestDto.Currency.RSD);

        return "checkout";
    }

    @PostMapping
    public String order(
            @ModelAttribute("orderDto") @Validated OrderDto orderDto,
            @RequestParam int amount,
            @RequestParam String stripeEmail,
            @RequestParam String stripeToken,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        List<OrderItemDto> cart = (List<OrderItemDto>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDto", orderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDto", result);
            return "redirect:/orders/checkout";
        }

        try {
            ChargeRequestDto chargeRequest = new ChargeRequestDto();
            chargeRequest.setDescription("Charge");
            chargeRequest.setCurrency(ChargeRequestDto.Currency.RSD);
            chargeRequest.setAmount(amount);
            chargeRequest.setStripeEmail(stripeEmail);
            chargeRequest.setStripeToken(stripeToken);
            Charge charge = stripeService.charge(chargeRequest);
        } catch (Exception ex) {
            System.out.println("*************STRIPE ERROR*******************");
            System.out.println(ex.getMessage());
            String message = messageSource.getMessage("label.orders.payment", null, locale);
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/orders/checkout";
        }

        UserDto userDto = (UserDto) session.getAttribute("user");
        orderDto.setItems(cart);
        orderDto.setUser(userDto);
        orderService.save(orderDto);

        String message = messageSource.getMessage("label.order.success", null, locale);
        redirectAttributes.addFlashAttribute("success", message);
        return "redirect:/orders";
    }

    private BigDecimal getTotal(List<OrderItemDto> cart) {
        BigDecimal total = BigDecimal.valueOf(0);

        for (OrderItemDto item : cart) {
            total = total.add(item.getAmount());
        }

        return total;
    }

    @ModelAttribute("orderDto")
    private OrderDto getOrderDto() {
        return new OrderDto();
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(orderValidator);
    }

}

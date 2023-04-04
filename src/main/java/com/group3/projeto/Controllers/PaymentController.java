package com.group3.projeto.Controllers;

import com.group3.projeto.dto.CheckoutDto;
import com.group3.projeto.services.PaymentService;
import com.paypal.api.payments.Links;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Payment;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public String payment(@ModelAttribute("checkout") CheckoutDto checkout) {
        try {
            Payment payment = paymentService.createPayment(checkout.getTotal(), checkout.getCurrency(), checkout.getMethod(),
                    checkout.getIntent(), "http://localhost:9090/" + CANCEL_URL,
                    "http://localhost:9090/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "cancel";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "success";
    }
}

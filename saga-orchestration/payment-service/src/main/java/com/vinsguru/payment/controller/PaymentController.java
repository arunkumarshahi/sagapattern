package com.vinsguru.payment.controller;

import com.vinsguru.dto.PaymentRequestDTO;
import com.vinsguru.dto.PaymentResponseDTO;
import com.vinsguru.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO){
        log.info("controller for debit = {}",requestDTO);
        return this.service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO requestDTO){

        log.info("controller for credit = {}",requestDTO);
        this.service.credit(requestDTO);
    }

}

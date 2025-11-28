package org.formation.simplecash.controller;

import org.formation.simplecash.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public void transfer(@RequestParam String source,
                         @RequestParam String destination,
                         @RequestParam BigDecimal amount) {
        transferService.transfer(source, destination, amount);
    }
}

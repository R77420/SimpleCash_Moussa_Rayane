package org.formation.simplecash.adapter.in.web;

import org.formation.simplecash.application.port.in.TransferUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferUseCase transferUseCase;

    public TransferController(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @PostMapping
    public void transfer(@RequestParam String source,
            @RequestParam String destination,
            @RequestParam BigDecimal amount) {
        transferUseCase.transfer(source, destination, amount);
    }
}

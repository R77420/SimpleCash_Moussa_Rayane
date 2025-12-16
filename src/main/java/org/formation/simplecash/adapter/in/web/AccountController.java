package org.formation.simplecash.adapter.in.web;

import org.formation.simplecash.adapter.in.web.dto.AccountDTO;
import org.formation.simplecash.adapter.in.web.dto.WebMapper;
import org.formation.simplecash.application.port.in.AccountUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountUseCase accountUseCase;
    private final WebMapper webMapper;

    public AccountController(AccountUseCase accountUseCase, WebMapper webMapper) {
        this.accountUseCase = accountUseCase;
        this.webMapper = webMapper;
    }

    @GetMapping("/{number}")
    public AccountDTO getAccount(@PathVariable String number) {
        return webMapper.toDTO(accountUseCase.getByAccountNumber(number));
    }

    @PostMapping("{number}/add")
    public AccountDTO addMoney(@PathVariable String number, @RequestBody BigDecimal amount) {
        return webMapper.toDTO(accountUseCase.addMoney(number, amount));
    }

    @PostMapping("{number}/remove")
    public AccountDTO removeMoney(@PathVariable String number, @RequestBody BigDecimal amount) {
        return webMapper.toDTO(accountUseCase.removeMoney(number, amount));
    }
}

package org.formation.simplecash.controller;

import org.formation.simplecash.entity.Account;
import org.formation.simplecash.service.AccountService;
import org.formation.simplecash.service.CurrentAccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService, CurrentAccountService currentAccountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{number}")
    public Account getAccount(@PathVariable String number) {
        return accountService.getByAccountNumber(number);
    }

    @PostMapping("{number}/add")
    public Account addMoney(@PathVariable String number, @RequestBody BigDecimal amount) {
        return accountService.addMoney(number,amount);
    }

    @PostMapping("{number}/remove")
    public Account removeMoney(@PathVariable String number, @RequestBody BigDecimal amount) {
        return accountService.removeMoney(number,amount);
    }
}

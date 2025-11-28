package org.formation.simplecash.controller;

import org.formation.simplecash.entity.Account;
import org.formation.simplecash.entity.CurrentAccount;
import org.formation.simplecash.service.AccountService;
import org.formation.simplecash.service.CurrentAccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/current-accounts")
public class CurrentAccountController {
    private final CurrentAccountService currentAccountService;
    private final AccountService accountService;
    public CurrentAccountController(CurrentAccountService currentAccountService, AccountService accountService) {
        this.currentAccountService = currentAccountService;
        this.accountService = accountService;
    }

    @PostMapping("/create/{clientId}")
    public CurrentAccount create(Long clientId) {
        return currentAccountService.createAccount(clientId);
    }

    @GetMapping("/{number}")
    public Account get(@PathVariable String number) {
        return accountService.getByAccountNumber(number);
    }

    @PostMapping("/{number}/add")
    public Account add(@PathVariable String number, @RequestBody BigDecimal amount) {
        return accountService.addMoney(number, amount);
    }

    @PostMapping("{number}/remove")
    public Account removeMoney(@PathVariable String number, @RequestBody BigDecimal amount) {
        return accountService.removeMoney(number,amount);
    }

    @GetMapping("/client/{clientId}")
    public List<Account> getAllAccounts(@PathVariable Long clientId) {
        return accountService.getAccountsOfClient(clientId);
    }
}

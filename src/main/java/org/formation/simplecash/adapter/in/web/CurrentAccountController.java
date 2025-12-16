package org.formation.simplecash.adapter.in.web;

import org.formation.simplecash.adapter.in.web.dto.AccountDTO;
import org.formation.simplecash.adapter.in.web.dto.CurrentAccountDTO;
import org.formation.simplecash.adapter.in.web.dto.WebMapper;
import org.formation.simplecash.application.port.in.AccountUseCase;
import org.formation.simplecash.application.port.in.CurrentAccountUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/current-accounts")
public class CurrentAccountController {
    private final CurrentAccountUseCase currentAccountUseCase;
    private final AccountUseCase accountUseCase;
    private final WebMapper webMapper;

    public CurrentAccountController(CurrentAccountUseCase currentAccountUseCase, AccountUseCase accountUseCase,
            WebMapper webMapper) {
        this.currentAccountUseCase = currentAccountUseCase;
        this.accountUseCase = accountUseCase;
        this.webMapper = webMapper;
    }

    @PostMapping("/create/{clientId}")
    public CurrentAccountDTO create(@PathVariable Long clientId) {
        return webMapper.toDTO(currentAccountUseCase.createAccount(clientId));
    }

    @GetMapping("/{number}")
    public AccountDTO get(@PathVariable String number) {
        return webMapper.toDTO(accountUseCase.getByAccountNumber(number));
    }

    @PostMapping("/{number}/add")
    public AccountDTO add(@PathVariable String number, @RequestBody BigDecimal amount) {
        return webMapper.toDTO(accountUseCase.addMoney(number, amount));
    }

    @PostMapping("{number}/remove")
    public AccountDTO removeMoney(@PathVariable String number, @RequestBody BigDecimal amount) {
        return webMapper.toDTO(accountUseCase.removeMoney(number, amount));
    }

    @GetMapping("/client/{clientId}")
    public List<AccountDTO> getAllAccounts(@PathVariable Long clientId) {
        return webMapper.toAccountDTOList(accountUseCase.getAccountsOfClient(clientId));
    }
}

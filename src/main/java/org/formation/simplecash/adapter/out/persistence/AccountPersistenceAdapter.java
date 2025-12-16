package org.formation.simplecash.adapter.out.persistence;

import org.formation.simplecash.adapter.out.persistence.entity.AccountEntity;
import org.formation.simplecash.adapter.out.persistence.mapper.AccountMapper;
import org.formation.simplecash.adapter.out.persistence.repository.AccountRepository;
import org.formation.simplecash.application.port.out.AccountPort;
import org.formation.simplecash.domain.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountPersistenceAdapter implements AccountPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountPersistenceAdapter(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        AccountEntity saved = accountRepository.save(entity);
        return accountMapper.toDomain(saved);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toDomain);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        AccountEntity entity = accountRepository.findByAccountNumber(accountNumber);
        return Optional.ofNullable(entity).map(accountMapper::toDomain);
    }

    @Override
    public List<Account> findByClientId(Long clientId) {
        return accountMapper.toDomainList(accountRepository.findByClientId(clientId));
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(accountMapper.toEntity(account));
    }

    @Override
    public void deleteAll(List<Account> accounts) {
        // Need to convert list -> entities -> deleteAll
        // But mapstruct toEntity list might not work if not defined.
        // We can just loop.
        accounts.forEach(this::delete);
    }
}

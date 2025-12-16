package org.formation.simplecash.config;

import org.formation.simplecash.application.port.out.AccountPort;
import org.formation.simplecash.application.port.out.AdvisorPort;
import org.formation.simplecash.application.port.out.ClientPort;
import org.formation.simplecash.application.port.in.AccountUseCase;
import org.formation.simplecash.application.service.AccountService;
import org.formation.simplecash.application.service.AdvisorService;
import org.formation.simplecash.application.service.ClientService;
import org.formation.simplecash.application.service.CurrentAccountService;
import org.formation.simplecash.application.service.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public AdvisorService advisorService(AdvisorPort advisorPort) {
        return new AdvisorService(advisorPort);
    }

    @Bean
    public ClientService clientService(ClientPort clientPort, AdvisorPort advisorPort, AccountPort accountPort) {
        return new ClientService(clientPort, advisorPort, accountPort);
    }

    @Bean
    public AccountService accountService(AccountPort accountPort) {
        return new AccountService(accountPort);
    }

    @Bean
    public CurrentAccountService currentAccountService(AccountPort accountPort, ClientPort clientPort) {
        return new CurrentAccountService(accountPort, clientPort);
    }

    @Bean
    public TransferService transferService(AccountUseCase accountUseCase) {
        return new TransferService(accountUseCase);
    }
}

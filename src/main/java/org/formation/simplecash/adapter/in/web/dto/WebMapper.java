package org.formation.simplecash.adapter.in.web.dto;

import org.formation.simplecash.domain.Account;
import org.formation.simplecash.domain.Advisor;
import org.formation.simplecash.domain.Client;
import org.formation.simplecash.domain.CurrentAccount;
import org.formation.simplecash.domain.SavingAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WebMapper {

    public abstract AdvisorDTO toDTO(Advisor advisor);

    public abstract Advisor toDomain(AdvisorDTO dto);

    public abstract ClientDTO toDTO(Client client);

    public abstract Client toDomain(ClientDTO dto);

    public abstract List<ClientDTO> toClientDTOList(List<Client> clients);

    public AccountDTO toDTO(Account account) {
        if (account == null) {
            return null;
        }
        if (account instanceof CurrentAccount) {
            return toDTO((CurrentAccount) account);
        } else if (account instanceof SavingAccount) {
            return toDTO((SavingAccount) account);
        }
        throw new IllegalArgumentException("Unknown account type: " + account.getClass());
    }

    public Account toDomain(AccountDTO dto) {
        if (dto == null) {
            return null;
        }
        if (dto instanceof CurrentAccountDTO) {
            return toDomain((CurrentAccountDTO) dto);
        } else if (dto instanceof SavingAccountDTO) {
            return toDomain((SavingAccountDTO) dto);
        }
        throw new IllegalArgumentException("Unknown DTO type: " + dto.getClass());
    }

    public abstract List<AccountDTO> toAccountDTOList(List<Account> accounts);

    public abstract CurrentAccountDTO toDTO(CurrentAccount account);

    public abstract CurrentAccount toDomain(CurrentAccountDTO dto);

    public abstract SavingAccountDTO toDTO(SavingAccount account);

    public abstract SavingAccount toDomain(SavingAccountDTO dto);
}

package org.formation.simplecash.adapter.out.persistence.mapper;

import org.formation.simplecash.adapter.out.persistence.entity.AccountEntity;
import org.formation.simplecash.adapter.out.persistence.entity.CurrentAccountEntity;
import org.formation.simplecash.adapter.out.persistence.entity.SavingAccountEntity;
import org.formation.simplecash.domain.Account;
import org.formation.simplecash.domain.CurrentAccount;
import org.formation.simplecash.domain.SavingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ClientMapper.class })
public abstract class AccountMapper {

    public Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof CurrentAccountEntity) {
            return toDomain((CurrentAccountEntity) entity);
        } else if (entity instanceof SavingAccountEntity) {
            return toDomain((SavingAccountEntity) entity);
        }
        throw new IllegalArgumentException("Unknown entity type: " + entity.getClass());
    }

    public AccountEntity toEntity(Account domain) {
        if (domain == null) {
            return null;
        }
        if (domain instanceof CurrentAccount) {
            return toEntity((CurrentAccount) domain);
        } else if (domain instanceof SavingAccount) {
            return toEntity((SavingAccount) domain);
        }
        throw new IllegalArgumentException("Unknown domain type: " + domain.getClass());
    }

    public abstract List<Account> toDomainList(List<AccountEntity> entities);

    @Mapping(target = "client", qualifiedByName = "ShallowClient")
    public abstract CurrentAccount toDomain(CurrentAccountEntity entity);

    public abstract CurrentAccountEntity toEntity(CurrentAccount domain);

    @Mapping(target = "client", qualifiedByName = "ShallowClient")
    public abstract SavingAccount toDomain(SavingAccountEntity entity);

    public abstract SavingAccountEntity toEntity(SavingAccount domain);
}

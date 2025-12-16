package org.formation.simplecash.adapter.out.persistence.mapper;

import org.formation.simplecash.adapter.out.persistence.entity.ClientEntity;
import org.formation.simplecash.domain.Client;
import org.mapstruct.Mapper;

import org.mapstruct.Named;
import org.mapstruct.Mapping;
import org.mapstruct.IterableMapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = { AdvisorMapper.class, AccountMapper.class })
public interface ClientMapper {
    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity clientEntity);

    List<Client> toDomainList(List<ClientEntity> clientEntities);

    @Named("ShallowClient")
    @Mapping(target = "advisor", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Client toDomainShallow(ClientEntity clientEntity);

    @Named("ShallowClientList")
    @IterableMapping(qualifiedByName = "ShallowClient")
    List<Client> toDomainListShallow(List<ClientEntity> clientEntities);
}

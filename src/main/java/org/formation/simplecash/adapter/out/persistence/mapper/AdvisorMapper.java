package org.formation.simplecash.adapter.out.persistence.mapper;

import org.formation.simplecash.adapter.out.persistence.entity.AdvisorEntity;
import org.formation.simplecash.domain.Advisor;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ClientMapper.class })
public interface AdvisorMapper {
    @Mapping(target = "clients", ignore = true)
    AdvisorEntity toEntity(Advisor advisor);

    @Mapping(target = "clients", qualifiedByName = "ShallowClientList")
    Advisor toDomain(AdvisorEntity advisorEntity);

    List<Advisor> toDomainList(List<AdvisorEntity> advisorEntities);
}

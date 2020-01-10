package fr.sigma.ca.service.mapper;

import fr.sigma.ca.dto.CommissionDTO;
import fr.sigma.ca.entite.Commission;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommissionMapper extends MapperGenerique<CommissionDTO, Commission> {
}

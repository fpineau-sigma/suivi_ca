package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Commission;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.CommissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommissionMapper extends MapperGenerique<CommissionDTO, Commission> {

}

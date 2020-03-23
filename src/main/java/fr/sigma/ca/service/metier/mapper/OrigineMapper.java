package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.domain.metier.Origine;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.OrigineDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrigineMapper extends MapperGenerique<OrigineDTO, Origine> {

}

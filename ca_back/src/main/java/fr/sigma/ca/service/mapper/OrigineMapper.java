package fr.sigma.ca.service.mapper;

import fr.sigma.ca.dto.OrigineDTO;
import fr.sigma.ca.entite.Origine;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrigineMapper extends MapperGenerique<OrigineDTO, Origine> {
}

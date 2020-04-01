package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Agence;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.AgenceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgenceMapper extends MapperGenerique<AgenceDTO, Agence> {

}

package fr.sigma.ca.service.mapper;

import fr.sigma.ca.dto.PersonneDTO;
import fr.sigma.ca.entite.Personne;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonneMapper extends MapperGenerique<PersonneDTO, Personne> {
}

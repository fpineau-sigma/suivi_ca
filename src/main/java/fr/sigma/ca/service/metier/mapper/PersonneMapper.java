package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.domain.metier.Personne;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.PersonneDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonneMapper extends MapperGenerique<PersonneDTO, Personne> {
}

package fr.sigma.ca.service.mapper;

import fr.sigma.ca.entities.Personne;
import fr.sigma.ca.dto.PersonneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonneMapper {

    PersonneMapper MAPPER = Mappers.getMapper(PersonneMapper.class);

    // MÃ©thode de conversion des Personnes
    PersonneDTO personneToDTO(Personne personne);

    Personne dtoToPersonne(PersonneDTO dto);
}

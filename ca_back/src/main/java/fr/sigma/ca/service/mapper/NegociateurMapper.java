package fr.sigma.ca.service.mapper;

import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.entite.Negociateur;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NegociateurMapper extends MapperGenerique<NegociateurDTO, Negociateur> {
}

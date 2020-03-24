package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Negociateur;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.NegociateurDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NegociateurMapper extends MapperGenerique<NegociateurDTO, Negociateur> {

}

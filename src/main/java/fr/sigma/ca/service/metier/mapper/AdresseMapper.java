package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Adresse;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.AdresseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdresseMapper extends MapperGenerique<AdresseDTO, Adresse> {

}

package fr.sigma.ca.service.mapper;

import fr.sigma.ca.entite.Adresse;
import fr.sigma.ca.dto.AdresseDTO;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdresseMapper extends MapperGenerique<AdresseDTO, Adresse> {
}

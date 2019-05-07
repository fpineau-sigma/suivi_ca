package fr.sigma.ca.service.mapper;

import fr.sigma.ca.domain.Adresse;
import fr.sigma.ca.dto.AdresseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdresseMapper {

    AdresseMapper MAPPER = Mappers.getMapper(AdresseMapper.class);

    // Méthodes de conversions des adresses
    AdresseDTO adresseToDTO(Adresse adresse);

    Adresse dtoToAdresse(AdresseDTO dto);
}

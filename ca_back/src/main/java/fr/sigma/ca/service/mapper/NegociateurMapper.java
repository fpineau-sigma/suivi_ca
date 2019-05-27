package fr.sigma.ca.service.mapper;

import fr.sigma.ca.entities.Negociateur;
import fr.sigma.ca.dto.NegociateurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NegociateurMapper {

    NegociateurMapper MAPPER = Mappers.getMapper(NegociateurMapper.class);

    // Méthode de conversion dse Negociateurs
    NegociateurDTO negociateurToDTO(Negociateur negociateur);

    Negociateur dtoToNegociateur(NegociateurDTO dto);
}

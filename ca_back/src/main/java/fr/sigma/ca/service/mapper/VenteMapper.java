package fr.sigma.ca.service.mapper;

import fr.sigma.ca.domain.Vente;
import fr.sigma.ca.dto.VenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VenteMapper {

    VenteMapper MAPPER = Mappers.getMapper(VenteMapper.class);

    // MÃ©thode de conversion des ventes
    VenteDTO venteToDTO(Vente vente);

    Vente dtoToVente(VenteDTO dto);
}

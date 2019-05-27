package fr.sigma.ca.service.mapper;

import fr.sigma.ca.entities.Commission;
import fr.sigma.ca.entities.Negociateur;
import fr.sigma.ca.dto.CommissionDTO;
import fr.sigma.ca.dto.NegociateurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommissionMapper {

    CommissionMapper MAPPER = Mappers.getMapper(CommissionMapper.class);

    // MÃ©thode de conversion des commissions
    CommissionDTO commissionToDTO(Commission commission);

    Commission dtoToCommission(CommissionDTO dto);

    NegociateurDTO negociateurToDTO(Negociateur negociateur);

    Negociateur dtoToNegociateur(NegociateurDTO dto);
}

package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Vente;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.VenteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
    uses = {CommissionMapper.class})
public interface VenteMapper extends MapperGenerique<VenteDTO, Vente> {

}

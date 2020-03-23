package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.domain.metier.Vente;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.VenteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenteMapper extends MapperGenerique<VenteDTO, Vente> {
}

package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.MontantTypeOrigine;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.MontantTypeOrigineDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MontantTypeOrigineMapper extends
    MapperGenerique<MontantTypeOrigineDTO, MontantTypeOrigine> {

}

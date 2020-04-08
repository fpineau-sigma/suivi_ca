package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Objectif;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.ObjectifDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
    uses = {MontantTypeOrigineMapper.class})
public interface ObjectifMapper extends MapperGenerique<ObjectifDTO, Objectif> {

}

package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.entite.metier.Exercice;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.ExerciceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciceMapper extends MapperGenerique<ExerciceDTO, Exercice> {

}

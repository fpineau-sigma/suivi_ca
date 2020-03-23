package fr.sigma.ca.service.metier.mapper;

import fr.sigma.ca.domain.metier.TypeDeBien;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import fr.sigma.ca.service.metier.dto.TypeDeBienDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeDeBienMapper extends MapperGenerique<TypeDeBienDTO, TypeDeBien> {

}

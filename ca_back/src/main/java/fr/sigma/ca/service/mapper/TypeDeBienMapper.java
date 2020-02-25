package fr.sigma.ca.service.mapper;

import fr.sigma.ca.dto.TypeDeBienDTO;
import fr.sigma.ca.entite.TypeDeBien;
import fr.sigma.ca.integration.persistence.MapperGenerique;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeDeBienMapper extends MapperGenerique<TypeDeBienDTO, TypeDeBien> {

}

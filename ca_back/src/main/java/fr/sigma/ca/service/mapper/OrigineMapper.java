package fr.sigma.ca.service.mapper;

        import fr.sigma.ca.domain.Origine;
        import fr.sigma.ca.dto.OrigineDTO;
        import org.mapstruct.Mapper;
        import org.mapstruct.factory.Mappers;

@Mapper
public interface OrigineMapper {
    OrigineMapper MAPPER = Mappers.getMapper(OrigineMapper.class);

    // Methode de conversion des origins
    OrigineDTO origineToDTO(Origine origine);

    Origine dtoToOrigine(OrigineDTO dto);
}

package fr.sigma.ca.service.mapper;

    import fr.sigma.ca.dto.VenteDTO;
    import fr.sigma.ca.entite.Vente;
    import fr.sigma.ca.integration.persistence.MapperGenerique;
    import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenteMapper extends MapperGenerique<VenteDTO, Vente> {
}

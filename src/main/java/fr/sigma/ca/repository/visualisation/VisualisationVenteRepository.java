package fr.sigma.ca.repository.visualisation;

import com.querydsl.core.types.Predicate;
import fr.sigma.ca.web.rest.visualisation.dto.VentesParMoisDto;
import java.util.List;

public interface VisualisationVenteRepository {

    List<VentesParMoisDto> countByMonth(Predicate predicate);
}

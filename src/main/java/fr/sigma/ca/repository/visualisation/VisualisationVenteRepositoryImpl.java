package fr.sigma.ca.repository.visualisation;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import fr.sigma.ca.entite.metier.QVente;
import fr.sigma.ca.entite.metier.Vente;
import fr.sigma.ca.web.rest.visualisation.dto.VentesParMoisDto;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository("VisualisationVenteRepository")
public class VisualisationVenteRepositoryImpl extends QuerydslRepositorySupport implements
    VisualisationVenteRepository {


    public VisualisationVenteRepositoryImpl() {
        super(Vente.class);
    }

    @Override
    public List<VentesParMoisDto> countByMonth(Predicate predicate) {
        QVente qVente = QVente.vente;

        return from(qVente)
            .select(Projections
                .constructor(VentesParMoisDto.class, qVente.dateActeAuthentique.month(),
                    qVente.count()))
            .groupBy(qVente.dateActeAuthentique.month())
            .orderBy(qVente.dateActeAuthentique.month().asc())
            .fetch();
    }
}

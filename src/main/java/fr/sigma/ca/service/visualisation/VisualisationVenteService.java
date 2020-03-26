package fr.sigma.ca.service.visualisation;

import fr.sigma.ca.entite.metier.QVente;
import fr.sigma.ca.repository.visualisation.VisualisationVenteRepository;
import fr.sigma.ca.web.rest.visualisation.dto.VentesParMoisDto;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisualisationVenteService {

    private final VisualisationVenteRepository repository;

    @Transactional
    public List<VentesParMoisDto> suiviVentes() {
        Integer anneeEnCours = LocalDate.now().getYear();
        return repository.countByMonth(QVente.vente.dateActeAuthentique.year().eq(anneeEnCours));
    }
}

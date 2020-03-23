package fr.sigma.ca.service.metier;

import fr.sigma.ca.domain.metier.Origine;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.OrigineRepository;
import fr.sigma.ca.service.metier.dto.OrigineDTO;
import fr.sigma.ca.service.metier.mapper.OrigineMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrigineService {

  private final OrigineRepository repository;
  private final OrigineMapper mapper;

  @Transactional
  public OrigineDTO creer(Origine origine) {
    ValidationAssistant.valider(origine);
    return mapper.toDto(repository.save(origine));
  }

  @Transactional
  public Collection<OrigineDTO> lister() {
    return mapper.toDto(repository.findAll());
  }

  @Transactional
  public OrigineDTO mettreAJour(Origine origine) {
    ValidationAssistant.valider(origine);
    Origine origineBdd = repository.findById(origine.getId())
        .orElseThrow(() -> new BusinessException(""));
    ObjetUtilitaire.merge(origineBdd, origine, Origine.class);
    return mapper.toDto(repository.save(origineBdd));
  }
}

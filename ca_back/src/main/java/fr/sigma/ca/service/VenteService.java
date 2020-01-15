package fr.sigma.ca.service;

import fr.sigma.ca.entite.Vente;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.VenteRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenteService {

  private final VenteRepository repository;

  @Transactional
  public Vente enregistrerVente(Vente vente) {
    ValidationAssistant.valider(vente);
    return repository.save(vente);
  }

  @Transactional
  public Collection<Vente> lister() {
    return repository.findAll();
  }
}

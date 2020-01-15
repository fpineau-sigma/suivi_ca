package fr.sigma.ca.service;

import fr.sigma.ca.entite.Personne;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.PersonneRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonneService {

  private final PersonneRepository repository;

  @Transactional
  public Personne creer(Personne personne) {
    ValidationAssistant.valider(personne);
    return repository.save(personne);
  }

  @Transactional
  public Collection<Personne> lister() {
    return repository.findAll();
  }
}

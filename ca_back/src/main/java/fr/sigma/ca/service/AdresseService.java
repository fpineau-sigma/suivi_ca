package fr.sigma.ca.service;

import fr.sigma.ca.entite.Adresse;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.AdresseRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdresseService {

  private final AdresseRepository repository;

  @Transactional
  public Adresse creer(Adresse adresse) {
    ValidationAssistant.valider(adresse);
    return repository.save(adresse);
  }

  @Transactional
  public Collection<Adresse> lister() {
    return repository.findAll();
  }
}

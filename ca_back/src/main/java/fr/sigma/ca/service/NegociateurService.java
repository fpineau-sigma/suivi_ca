package fr.sigma.ca.service;

import fr.sigma.ca.entite.Negociateur;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.NegociateurRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NegociateurService {

  private final NegociateurRepository repository;

  @Transactional
  public Negociateur creer(Negociateur negociateur) {
    ValidationAssistant.valider(negociateur);
    return repository.save(negociateur);
  }

  @Transactional
  public Negociateur mettreAJour(Negociateur negociateur) {
    ValidationAssistant.valider(negociateur);
    Negociateur negociateurBdd = repository.findById(negociateur.getId())
        .orElseThrow(() -> new BusinessException(""));
    ObjetUtilitaire.merge(negociateurBdd, negociateur, Negociateur.class);
    return repository.save(negociateurBdd);
  }

  @Transactional
  public Collection<Negociateur> lister() {
    return repository.findAll();
  }
}

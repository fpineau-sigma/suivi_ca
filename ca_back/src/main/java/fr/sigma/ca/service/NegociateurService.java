package fr.sigma.ca.service;

import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.entite.Negociateur;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.NegociateurRepository;
import fr.sigma.ca.service.mapper.NegociateurMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NegociateurService {

  private final NegociateurRepository repository;
  private final NegociateurMapper mapper;

  @Transactional
  public Negociateur creer(NegociateurDTO negociateurDTO) {
    ValidationAssistant.valider(negociateurDTO);
    Negociateur negociateur = mapper.toEntity(negociateurDTO);
    return repository.save(negociateur);
  }

  @Transactional
  public Negociateur mettreAJour(NegociateurDTO negociateurDTO) {
    ValidationAssistant.valider(negociateurDTO);
    Negociateur negociateur = mapper.toEntity(negociateurDTO);
    Negociateur negociateurBdd = repository.findById(negociateurDTO.getId())
        .orElseThrow(() -> new BusinessException(""));
    ObjetUtilitaire.merge(negociateurBdd, negociateur, Negociateur.class);
    return repository.save(negociateurBdd);
  }

  @Transactional
  public List<Negociateur> lister() {
    return repository.findAll();
  }
}

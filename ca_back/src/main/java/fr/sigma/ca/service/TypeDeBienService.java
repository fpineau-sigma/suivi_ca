package fr.sigma.ca.service;

import fr.sigma.ca.dto.TypeDeBienDTO;
import fr.sigma.ca.entite.TypeDeBien;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.TypeDeBienRepository;
import fr.sigma.ca.service.mapper.TypeDeBienMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TypeDeBienService {

  private final TypeDeBienRepository repository;
  private final TypeDeBienMapper mapper;

  @Transactional
  public TypeDeBienDTO creer(TypeDeBien typeDeBien) {
    ValidationAssistant.valider(typeDeBien);
    return mapper.toDto(repository.save(typeDeBien));
  }

  @Transactional
  public Collection<TypeDeBienDTO> lister() {
    return mapper.toDto(repository.findAll());
  }

  @Transactional
  public TypeDeBienDTO mettreAJour(TypeDeBien typeDeBien) {
    ValidationAssistant.valider(typeDeBien);
    TypeDeBien typeDeBienBdd = repository.findById(typeDeBien.getId())
        .orElseThrow(() -> new BusinessException(""));
    ObjetUtilitaire.merge(typeDeBienBdd, typeDeBien, TypeDeBien.class);
    return mapper.toDto(repository.save(typeDeBienBdd));
  }
}

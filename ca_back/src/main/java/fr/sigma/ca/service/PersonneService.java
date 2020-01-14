package fr.sigma.ca.service;

import fr.sigma.ca.dto.PersonneDTO;
import fr.sigma.ca.entite.Personne;
import fr.sigma.ca.repository.PersonneRepository;
import fr.sigma.ca.service.mapper.PersonneMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonneService {
    
    private final PersonneRepository repository;
    private final PersonneMapper mapper;

    @Transactional
    public Long enregistrerPersonne(PersonneDTO personneDTO) {
        Personne personne = mapper.toEntity(personneDTO);
        Personne persistEntity = repository.save(personne);
        return persistEntity.getId();
    }

    @Transactional
    public List<PersonneDTO> findAll() {
        List<PersonneDTO> personneDTOS = new ArrayList<>();
        Iterable<Personne> PersonnesEntities = repository.findAll();
        PersonnesEntities.forEach(personne ->{
            PersonneDTO personneDTO = mapper.toDto(personne);
            personneDTOS.add(personneDTO);
        });
        return personneDTOS;
    }

    @Transactional
    public long countAll() {
        return repository.count();
    }
}

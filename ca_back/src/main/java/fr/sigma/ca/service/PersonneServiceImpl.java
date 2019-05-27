package fr.sigma.ca.service;

import fr.sigma.ca.entities.Personne;
import fr.sigma.ca.dto.PersonneDTO;
import fr.sigma.ca.repository.PersonneRepository;
import fr.sigma.ca.service.mapper.PersonneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("PersonneService")
@Transactional
public class PersonneServiceImpl implements PersonneService {


    @Autowired
    private PersonneRepository personneRepository;

    @Override
    public UUID enregistrerPersonne(PersonneDTO personneDTO) {
        Personne personne = PersonneMapper.MAPPER.dtoToPersonne(personneDTO);
        Personne persistEntity = personneRepository.save(personne);
        return persistEntity.getId();
    }

    @Override
    public List<PersonneDTO> findAll() {
        List<PersonneDTO> personneDTOS = new ArrayList<>();
        Iterable<Personne> PersonnesEntities = personneRepository.findAll();
        PersonnesEntities.forEach(personne ->{
            PersonneDTO personneDTO = PersonneMapper.MAPPER.personneToDTO(personne);
            personneDTOS.add(personneDTO);
        });
        return personneDTOS;
    }

    @Override
    public long countAll() {
        return personneRepository.count();
    }
}

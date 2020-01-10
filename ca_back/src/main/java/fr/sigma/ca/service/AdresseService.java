package fr.sigma.ca.service;

import fr.sigma.ca.entite.Adresse;
import fr.sigma.ca.dto.AdresseDTO;
import fr.sigma.ca.repository.AdresseRepository;
import fr.sigma.ca.service.mapper.AdresseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdresseService {

    private final AdresseRepository repository;
    private final AdresseMapper mapper;

    @Transactional
    public UUID enregistrerAdresse(AdresseDTO adresseDTO) {
        Adresse adresse = mapper.toEntity(adresseDTO);
        Adresse persistEntity = repository.save(adresse);
        return persistEntity.getId();
    }

    @Transactional
    public List<AdresseDTO> findAll() {
        List<AdresseDTO> adresseDTOS = new ArrayList<>();
        Iterable<Adresse> AdressesEntities = repository.findAll();
        AdressesEntities.forEach(adresse ->{
            AdresseDTO adresseDTO = mapper.toDto(adresse);
            adresseDTOS.add(adresseDTO);
        });
        return adresseDTOS;
    }

    @Transactional
    public long countAll() {
        return repository.count();
    }
}

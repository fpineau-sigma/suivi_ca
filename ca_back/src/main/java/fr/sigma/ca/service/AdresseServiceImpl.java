package fr.sigma.ca.service;

import fr.sigma.ca.entities.Adresse;
import fr.sigma.ca.dto.AdresseDTO;
import fr.sigma.ca.repository.AdresseRepository;
import fr.sigma.ca.service.mapper.AdresseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("AdresseService")
@Transactional
public class AdresseServiceImpl implements AdresseService{

    @Autowired
    private AdresseRepository adresseRepository;

    @Override
    public UUID enregistrerAdresse(AdresseDTO adresseDTO) {
        Adresse adresse = AdresseMapper.MAPPER.dtoToAdresse(adresseDTO);
        Adresse persistEntity = adresseRepository.save(adresse);
        return persistEntity.getId();
    }

    @Override
    public List<AdresseDTO> findAll() {
        List<AdresseDTO> adresseDTOS = new ArrayList<>();
        Iterable<Adresse> AdressesEntities = adresseRepository.findAll();
        AdressesEntities.forEach(adresse ->{
            AdresseDTO adresseDTO = AdresseMapper.MAPPER.adresseToDTO(adresse);
            adresseDTOS.add(adresseDTO);
        });
        return adresseDTOS;
    }

    @Override
    public long countAll() {
        return adresseRepository.count();
    }
}

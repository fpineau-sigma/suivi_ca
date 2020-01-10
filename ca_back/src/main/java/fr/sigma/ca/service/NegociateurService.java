package fr.sigma.ca.service;

import fr.sigma.ca.entite.Negociateur;
import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.repository.CommissionRepository;
import fr.sigma.ca.repository.NegociateurRepository;
import fr.sigma.ca.service.mapper.CommissionMapper;
import fr.sigma.ca.service.mapper.NegociateurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NegociateurService {
    
    private final NegociateurRepository repository;
    private final NegociateurMapper mapper;

    @Transactional
    public UUID enregistrerNegociateur(NegociateurDTO negociateurDTO) {
        Negociateur negociateur = mapper.toEntity(negociateurDTO);
        Negociateur persistEntity = repository.save(negociateur);
        return persistEntity.getId();
    }

    @Transactional
    public List<NegociateurDTO> findAll() {
        List<NegociateurDTO> negociateurDTOS = new ArrayList<>();
        Iterable<Negociateur> NegociateursEntities = repository.findAll();
        NegociateursEntities.forEach(negociateur ->{
            NegociateurDTO negociateurDTO = mapper.toDto(negociateur);
            negociateurDTOS.add(negociateurDTO);
        });
        return negociateurDTOS;
    }

    @Transactional
    public long countAll() {
        return repository.count();
    }
}

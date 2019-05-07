package fr.sigma.ca.service;

import fr.sigma.ca.domain.Negociateur;
import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.repository.NegociateurRepository;
import fr.sigma.ca.service.mapper.NegociateurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("NegociateurService")
@Transactional
public class NegociateurServiceImpl implements NegociateurService{


    @Autowired
    private NegociateurRepository negociateurRepository;

    @Override
    public UUID enregistrerNegociateur(NegociateurDTO negociateurDTO) {
        Negociateur negociateur = NegociateurMapper.MAPPER.dtoToNegociateur(negociateurDTO);
        Negociateur persistEntity = negociateurRepository.save(negociateur);
        return persistEntity.getId();
    }

    @Override
    public List<NegociateurDTO> findAll() {
        List<NegociateurDTO> negociateurDTOS = new ArrayList<>();
        Iterable<Negociateur> NegociateursEntities = negociateurRepository.findAll();
        NegociateursEntities.forEach(negociateur ->{
            NegociateurDTO negociateurDTO = NegociateurMapper.MAPPER.negociateurToDTO(negociateur);
            negociateurDTOS.add(negociateurDTO);
        });
        return negociateurDTOS;
    }

    @Override
    public long countAll() {
        return negociateurRepository.count();
    }
}

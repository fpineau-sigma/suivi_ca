package fr.sigma.ca.service;

import fr.sigma.ca.entities.Vente;
import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.repository.VenteRepository;
import fr.sigma.ca.service.mapper.VenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("VenteService")
@Transactional
public class VenteServiceImpl implements VenteService{

    @Autowired
    private VenteRepository venteRepository;

    @Override
    public UUID enregistrerVente(VenteDTO venteDTO) {
        Vente vente = VenteMapper.MAPPER.dtoToVente(venteDTO);
        Vente persistEntity = venteRepository.save(vente);
        return persistEntity.getId();
    }

    @Override
    public List<VenteDTO> findAll() {
        List<VenteDTO> venteDTOS = new ArrayList<>();
        Iterable<Vente> VentesEntities = venteRepository.findAll();
        VentesEntities.forEach(vente ->{
            VenteDTO venteDTO = VenteMapper.MAPPER.venteToDTO(vente);
            venteDTOS.add(venteDTO);
        });
        return venteDTOS;
    }

    @Override
    public long countAll() {
        return venteRepository.count();
    }
}

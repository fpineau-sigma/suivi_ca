package fr.sigma.ca.service;

import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.entite.Vente;
import fr.sigma.ca.repository.VenteRepository;
import fr.sigma.ca.service.mapper.VenteMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenteService{
    
    private final VenteRepository repository;
    private final VenteMapper mapper;

    @Transactional
    public UUID enregistrerVente(VenteDTO venteDTO) {
        Vente vente = mapper.toEntity(venteDTO);
        Vente persistEntity = repository.save(vente);
        return persistEntity.getId();
    }

    @Transactional
    public List<VenteDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional
    public long countAll() {
        return repository.count();
    }
}

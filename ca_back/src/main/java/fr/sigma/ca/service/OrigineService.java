package fr.sigma.ca.service;

import fr.sigma.ca.entite.Origine;
import fr.sigma.ca.dto.OrigineDTO;
import fr.sigma.ca.repository.OrigineRepository;
import fr.sigma.ca.service.mapper.OrigineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrigineService {

    private final OrigineRepository repository;
    private final OrigineMapper mapper;

    @Transactional
    public UUID enregistrerOrigine(OrigineDTO origineDTO) {
        Origine origine = mapper.toEntity(origineDTO);
        Origine persistEntity = repository.save(origine);
        return persistEntity.getId();
    }

    @Transactional
    public List<OrigineDTO> findAll() {
        List<OrigineDTO> origineDTOS = new ArrayList<>();
        Iterable<Origine> OriginesEntities = repository.findAll();
        OriginesEntities.forEach(origine ->{
            OrigineDTO origineDTO = mapper.toDto(origine);
            origineDTOS.add(origineDTO);
        });
        return origineDTOS;
    }

    @Transactional
    public long countAll() {
        return repository.count();
    }
}

package fr.sigma.ca.service;

import fr.sigma.ca.entities.Origine;
import fr.sigma.ca.dto.OrigineDTO;
import fr.sigma.ca.repository.OrigineRepository;
import fr.sigma.ca.service.mapper.OrigineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("OrigineService")
@Transactional
public class OrigineServiceImpl implements OrigineService{

    @Autowired
    private OrigineRepository origineRepository;

    @Override
    public UUID enregistrerOrigine(OrigineDTO origineDTO) {
        Origine origine = OrigineMapper.MAPPER.dtoToOrigine(origineDTO);
        Origine persistEntity = origineRepository.save(origine);
        return persistEntity.getId();
    }

    @Override
    public List<OrigineDTO> findAll() {
        List<OrigineDTO> origineDTOS = new ArrayList<>();
        Iterable<Origine> OriginesEntities = origineRepository.findAll();
        OriginesEntities.forEach(origine ->{
            OrigineDTO origineDTO = OrigineMapper.MAPPER.origineToDTO(origine);
            origineDTOS.add(origineDTO);
        });
        return origineDTOS;
    }

    @Override
    public long countAll() {
        return origineRepository.count();
    }

}

package fr.sigma.ca.service;

import fr.sigma.ca.entite.Commission;
import fr.sigma.ca.dto.CommissionDTO;
import fr.sigma.ca.repository.CommissionRepository;
import fr.sigma.ca.service.mapper.CommissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRepository repository;
    private final CommissionMapper mapper;

    @Transactional
    public UUID enregistrerCommission(CommissionDTO commissionDTO) {
        Commission commission = mapper.toEntity(commissionDTO);
        Commission persistEntity = repository.save(commission);
        return persistEntity.getId();
    }

    @Transactional
    public List<CommissionDTO> findAll() {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        //Iterable<Commission> CommissionsEntities = repository.findAllByOrderByNegociateurAsc();
        Iterable<Commission> CommissionsEntities = repository.findAll();
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = mapper.toDto(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }

    @Transactional
    public long countAll() {
        return repository.count();
    }

    @Transactional
    public List<CommissionDTO> find(String nomCourt) {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Iterable<Commission> CommissionsEntities = repository.findByNegociateur_nomCourt(nomCourt);
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = mapper.toDto(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }

    @Transactional
    public List<CommissionDTO> find(Date date) {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Iterable<Commission> CommissionsEntities = repository.findByDateVente(date);
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = mapper.toDto(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }

    @Transactional
    public List<CommissionDTO> find(String nomCourt, Date date) {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Iterable<Commission> CommissionsEntities = repository.findByNegociateur_nomCourtAndDateVente(nomCourt, date);
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = mapper.toDto(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }
}

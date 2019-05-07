package fr.sigma.ca.service;

import fr.sigma.ca.domain.Commission;
import fr.sigma.ca.dto.CommissionDTO;
import fr.sigma.ca.repository.CommissionRepository;
import fr.sigma.ca.service.mapper.CommissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("CommissionService")
@Transactional
public class CommissionServiceImpl implements CommissionService{

    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public UUID enregistrerCommission(CommissionDTO commissionDTO) {
        Commission commission = CommissionMapper.MAPPER.dtoToCommission(commissionDTO);
        Commission persistEntity = commissionRepository.save(commission);
        return persistEntity.getId();
    }

    @Override
    public List<CommissionDTO> findAll() {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        //Iterable<Commission> CommissionsEntities = commissionRepository.findAllByOrderByNegociateurAsc();
        Iterable<Commission> CommissionsEntities = commissionRepository.findAll();
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = CommissionMapper.MAPPER.commissionToDTO(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }

    @Override
    public List<Object> findCommissionWithAdresse() {
        List<Object> finds = commissionRepository.findCommissionEntreeWithAdresse();
        finds.addAll(commissionRepository.findCommissionSortieWithAdresse());

        return finds;
    }

    @Override
    public long countAll() {
        return commissionRepository.count();
    }

    @Override
    public List<CommissionDTO> find(String nomCourt) {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Iterable<Commission> CommissionsEntities = commissionRepository.findByNegociateur_nomCourt(nomCourt);
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = CommissionMapper.MAPPER.commissionToDTO(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }

    @Override
    public List<CommissionDTO> find(Date date) {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Iterable<Commission> CommissionsEntities = commissionRepository.findByDateVente(date);
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = CommissionMapper.MAPPER.commissionToDTO(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }

    @Override
    public List<CommissionDTO> find(String nomCourt, Date date) {
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Iterable<Commission> CommissionsEntities = commissionRepository.findByNegociateur_nomCourtAndDateVente(nomCourt, date);
        CommissionsEntities.forEach(commission ->{
            CommissionDTO commissionDTO = CommissionMapper.MAPPER.commissionToDTO(commission);
            commissionDTOS.add(commissionDTO);
        });
        return commissionDTOS;
    }
}

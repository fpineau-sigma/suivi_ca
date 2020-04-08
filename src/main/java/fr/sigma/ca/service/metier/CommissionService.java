package fr.sigma.ca.service.metier;

import com.querydsl.core.BooleanBuilder;
import fr.sigma.ca.entite.metier.Commission;
import fr.sigma.ca.entite.metier.QCommission;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.CommissionRepository;
import fr.sigma.ca.service.metier.dto.CommissionDTO;
import fr.sigma.ca.service.metier.mapper.CommissionMapper;
import fr.sigma.ca.web.rest.metier.dto.CriteresRechercheCommissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRepository repository;
    private final CommissionMapper mapper;

    @Transactional
    public Commission creer(Commission commission) {
        ValidationAssistant.valider(commission);
        return repository.save(commission);
    }

    @Transactional
    public Page<CommissionDTO> lister(
        CriteresRechercheCommissionDto criteresRechercheVenCommissionDto,
        Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();
        if (null != criteresRechercheVenCommissionDto) {
            predicate.and(QCommission.commission.negociateur
                .nomCourt.eq(criteresRechercheVenCommissionDto.getNegociateur().getNomCourt()));
        }
        return repository.findAll(predicate, pageable).map(mapper::toDto);
    }

    @Transactional
    public Commission mettreAJour(Commission commission) {
        ValidationAssistant.valider(commission);
        if (null == commission.getId()) {
            return repository.save(commission);
        } else {
            Commission commissionBdd = repository.findById(commission.getId())
                .orElseThrow(() -> new BusinessException(""));
            ObjetUtilitaire.merge(commissionBdd, commission, Commission.class);
            return repository.save(commissionBdd);
        }
    }

}

package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.CommissionService;
import fr.sigma.ca.service.metier.dto.CommissionDTO;
import fr.sigma.ca.web.rest.metier.dto.CriteresRechercheCommissionDto;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/commissions")
@RequiredArgsConstructor
public class CommissionController {

    private final CommissionService service;

    @PostMapping("/lister")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Page<CommissionDTO> getCommissions(@ApiParam Pageable pageable,
        @RequestBody(required = false) CriteresRechercheCommissionDto criteresRechercheCommissionDto) {
        return service.lister(criteresRechercheCommissionDto, pageable);
    }
}

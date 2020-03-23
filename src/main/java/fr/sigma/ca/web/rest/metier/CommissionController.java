package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.CommissionService;
import fr.sigma.ca.service.metier.dto.CommissionDTO;
import fr.sigma.ca.service.metier.mapper.CommissionMapper;
import java.util.Collection;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/commissions")
@RequiredArgsConstructor
public class CommissionController {

  private final CommissionService service;
  private final CommissionMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<CommissionDTO> getCommissions(
      @RequestParam(value = "nomCourt", required = false) String nomCourt,
      @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "MM/yyyy") Date dateVente) {
    return mapper.toDto(service.lister());
  }
}

package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.AdresseService;
import fr.sigma.ca.service.metier.dto.AdresseDTO;
import fr.sigma.ca.service.metier.mapper.AdresseMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/adresses")
@RequiredArgsConstructor
public class AdresseController {

  private final AdresseService service;
  private final AdresseMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<AdresseDTO> lister() {
    return mapper.toDto(service.lister());
  }
}

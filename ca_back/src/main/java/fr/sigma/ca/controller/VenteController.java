package fr.sigma.ca.controller;

import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.service.VenteService;
import fr.sigma.ca.service.mapper.VenteMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ventes")
@RequiredArgsConstructor
public class VenteController {

  private final VenteService service;
  private final VenteMapper mapper;

  @GetMapping()
  public Collection<VenteDTO> lister() {
    return mapper.toDto(service.lister());
  }
}

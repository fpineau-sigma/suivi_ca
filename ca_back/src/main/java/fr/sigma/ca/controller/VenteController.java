package fr.sigma.ca.controller;

import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.service.VenteService;
import fr.sigma.ca.service.mapper.VenteMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ventes")
@RequiredArgsConstructor
public class VenteController {

  private final VenteService service;
  private final VenteMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<VenteDTO> lister() {
    return service.lister();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public VenteDTO lire(@PathVariable("id") Long id) {
    return service.lire(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public VenteDTO modifier(
      @RequestBody VenteDTO venteDTO, @PathVariable("id") Long id) {
    venteDTO.setId(id);
    return service.mettreAJour(mapper.toEntity(venteDTO));
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public VenteDTO creer(@RequestBody VenteDTO venteDTO) {
    return service.enregistrerVente(mapper.toEntity(venteDTO));
  }
}

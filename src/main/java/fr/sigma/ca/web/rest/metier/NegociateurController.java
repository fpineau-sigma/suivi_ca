package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.NegociateurService;
import fr.sigma.ca.service.metier.dto.NegociateurDTO;
import fr.sigma.ca.service.metier.mapper.NegociateurMapper;
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
@RequestMapping(path = "/api/negociateurs")
@RequiredArgsConstructor
public class NegociateurController {

  private final NegociateurService negociateurService;
  private final NegociateurMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<NegociateurDTO> getNegociateurs() {
    return mapper.toDto(negociateurService.lister());
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public NegociateurDTO creer(@RequestBody NegociateurDTO negociateurDTO) {
    return mapper.toDto(negociateurService.creer(mapper.toEntity(negociateurDTO)));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public NegociateurDTO modifier(
      @RequestBody NegociateurDTO negociateurDTO, @PathVariable("id") Long id) {
    negociateurDTO.setId(id);
    return mapper.toDto(negociateurService.mettreAJour(mapper.toEntity(negociateurDTO)));
  }
}

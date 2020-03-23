package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.OrigineService;
import fr.sigma.ca.service.metier.dto.OrigineDTO;
import fr.sigma.ca.service.metier.mapper.OrigineMapper;
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
@RequestMapping(path = "/api/origines")
@RequiredArgsConstructor
public class OrigineController {

  private final OrigineService origineService;
  private final OrigineMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<OrigineDTO> lister() {
    return origineService.lister();
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public OrigineDTO creer(@RequestBody OrigineDTO origineDTO) {
    return origineService.creer(mapper.toEntity(origineDTO));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public OrigineDTO modifier(
      @RequestBody OrigineDTO origineDTO, @PathVariable("id") Long id) {
    origineDTO.setId(id);
    return origineService.mettreAJour(mapper.toEntity(origineDTO));
  }
}

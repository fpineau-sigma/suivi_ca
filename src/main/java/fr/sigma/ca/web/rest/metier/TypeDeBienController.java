package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.TypeDeBienService;
import fr.sigma.ca.service.metier.dto.TypeDeBienDTO;
import fr.sigma.ca.service.metier.mapper.TypeDeBienMapper;
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
@RequestMapping(path = "/api/typedebiens")
@RequiredArgsConstructor
public class TypeDeBienController {

  private final TypeDeBienService typeDeBienService;
  private final TypeDeBienMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<TypeDeBienDTO> lister() {
    return typeDeBienService.lister();
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public TypeDeBienDTO creer(@RequestBody TypeDeBienDTO typeDeBienDTO) {
    return typeDeBienService.creer(mapper.toEntity(typeDeBienDTO));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public TypeDeBienDTO modifier(
      @RequestBody TypeDeBienDTO typeDeBienDTO, @PathVariable("id") Long id) {
    typeDeBienDTO.setId(id);
    return typeDeBienService.mettreAJour(mapper.toEntity(typeDeBienDTO));
  }
}

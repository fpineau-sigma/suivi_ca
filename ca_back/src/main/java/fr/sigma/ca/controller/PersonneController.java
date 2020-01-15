package fr.sigma.ca.controller;

import fr.sigma.ca.dto.PersonneDTO;
import fr.sigma.ca.service.PersonneService;
import fr.sigma.ca.service.mapper.PersonneMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/personnes")
@RequiredArgsConstructor
public class PersonneController {

  private final PersonneService service;
  private final PersonneMapper mapper;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<PersonneDTO> lister() {
    return mapper.toDto(service.lister());
  }
}

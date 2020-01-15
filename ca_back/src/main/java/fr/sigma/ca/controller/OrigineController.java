package fr.sigma.ca.controller;

import fr.sigma.ca.entite.Origine;
import fr.sigma.ca.service.OrigineService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/origines")
@RequiredArgsConstructor
public class OrigineController {

  private final OrigineService origineService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Collection<Origine> lister() {
    return origineService.lister();
  }
}

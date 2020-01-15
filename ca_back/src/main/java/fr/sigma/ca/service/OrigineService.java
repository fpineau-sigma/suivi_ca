package fr.sigma.ca.service;

import fr.sigma.ca.entite.Origine;
import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrigineService {

  public Collection<Origine> lister() {
    return Arrays.asList(Origine.values());
  }
}

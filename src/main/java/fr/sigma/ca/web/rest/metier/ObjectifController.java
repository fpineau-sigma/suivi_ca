package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.ObjectifService;
import fr.sigma.ca.service.metier.dto.ObjectifDTO;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/objectifs")
@RequiredArgsConstructor
public class ObjectifController {

    private final ObjectifService objectifService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<ObjectifDTO> lister(@RequestHeader("exercice") Long exerciceId) {
        return objectifService.lister(exerciceId);
    }
}

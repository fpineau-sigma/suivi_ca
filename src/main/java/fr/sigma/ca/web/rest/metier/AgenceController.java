package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.AgenceService;
import fr.sigma.ca.service.metier.dto.AgenceDTO;
import fr.sigma.ca.service.metier.mapper.AgenceMapper;
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
@RequestMapping(path = "/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final AgenceService service;
    private final AgenceMapper mapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<AgenceDTO> getAgences() {
        return service.lister();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO creer(@RequestBody AgenceDTO agenceDTO) {
        return service.creer(mapper.toEntity(agenceDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AgenceDTO modifier(
        @RequestBody AgenceDTO agenceDTO, @PathVariable("id") Long id) {
        agenceDTO.setId(id);
        return service.mettreAJour(mapper.toEntity(agenceDTO));
    }
}

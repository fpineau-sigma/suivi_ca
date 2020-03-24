package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.VenteService;
import fr.sigma.ca.service.metier.dto.VenteDTO;
import fr.sigma.ca.service.metier.mapper.VenteMapper;
import fr.sigma.ca.web.rest.metier.dto.CriteresRechercheVenteDto;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(path = "/api/ventes")
@RequiredArgsConstructor
public class VenteController {

    private final VenteService service;
    private final VenteMapper mapper;

    @PostMapping("/lister")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public Page<VenteDTO> lister(@ApiParam Pageable pageable,
        @RequestBody(required = false) CriteresRechercheVenteDto criteresRechercheVenteDto) {
        return service.lister(criteresRechercheVenteDto, pageable);
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VenteDTO creer(@RequestBody VenteDTO venteDTO) {
        return service.enregistrerVente(mapper.toEntity(venteDTO));
    }
}

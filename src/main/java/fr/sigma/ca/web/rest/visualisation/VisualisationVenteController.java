package fr.sigma.ca.web.rest.visualisation;

import fr.sigma.ca.service.visualisation.VisualisationVenteService;
import fr.sigma.ca.web.rest.visualisation.dto.VentesParMoisDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/visualisation/ventes")
@RequiredArgsConstructor
public class VisualisationVenteController {

    private final VisualisationVenteService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<VentesParMoisDto> ventesParMois() {
        return service.suiviVentes();
    }
}

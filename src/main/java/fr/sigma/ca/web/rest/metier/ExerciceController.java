package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.ExerciceService;
import fr.sigma.ca.service.metier.dto.ExerciceDTO;
import fr.sigma.ca.service.metier.mapper.ExerciceMapper;
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
@RequestMapping(path = "/api/exercices")
@RequiredArgsConstructor
public class ExerciceController {

    private final ExerciceService service;
    private final ExerciceMapper mapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<ExerciceDTO> getExercices() {
        return service.lister();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciceDTO creer(@RequestBody ExerciceDTO exerciceDTO) {
        return service.creer(mapper.toEntity(exerciceDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ExerciceDTO modifier(
        @RequestBody ExerciceDTO exerciceDTO, @PathVariable("id") Long id) {
        exerciceDTO.setId(id);
        return service.mettreAJour(mapper.toEntity(exerciceDTO));
    }
}

package fr.sigma.ca.controller;

import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.service.NegociateurService;
import fr.sigma.ca.service.mapper.NegociateurMapper;
import java.util.List;
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
@RequestMapping(path="/negociateurs")
@RequiredArgsConstructor
public class NegociateurController {

    private final NegociateurService negociateurService;
    private final NegociateurMapper mapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<NegociateurDTO> getNegociateurs(){
        return mapper.toDto(negociateurService.lister());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NegociateurDTO creer(@RequestBody NegociateurDTO negociateurDTO) {
        return mapper.toDto(negociateurService.creer(negociateurDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NegociateurDTO modifier(
        @RequestBody NegociateurDTO negociateurDTO, @PathVariable("id") Long id) {
        negociateurDTO.setId(id);
        return mapper.toDto(negociateurService.mettreAJour(negociateurDTO));
    }
}

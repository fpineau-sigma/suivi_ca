package fr.sigma.ca.controller;

import com.google.common.collect.Lists;
import fr.sigma.ca.dto.PersonneDTO;
import fr.sigma.ca.service.PersonneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping()
    public ResponseEntity<List<PersonneDTO>> getPersonnes(){
        return ResponseEntity.ok(Lists.newArrayList(personneService.findAll()));
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> countPersonnes(){
        return ResponseEntity.ok(personneService.countAll());
    }
}

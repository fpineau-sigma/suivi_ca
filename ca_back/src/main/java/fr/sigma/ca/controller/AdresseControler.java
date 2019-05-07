package fr.sigma.ca.controller;

import com.google.common.collect.Lists;
import fr.sigma.ca.dto.AdresseDTO;
import fr.sigma.ca.service.AdresseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/adresses")
public class AdresseControler {

    @Autowired
    private AdresseService adresseService;

    @GetMapping()
    public ResponseEntity<List<AdresseDTO>> getAdresses(){
        return ResponseEntity.ok(Lists.newArrayList(adresseService.findAll()));
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> countAdresses(){
        return ResponseEntity.ok(adresseService.countAll());
    }
}

package fr.sigma.ca.controller;

import com.google.common.collect.Lists;
import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.service.VenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/ventes")
public class VenteController {

    @Autowired
    private VenteService venteService;

    @GetMapping()
    public ResponseEntity<List<VenteDTO>> getVentes(){
        return ResponseEntity.ok(Lists.newArrayList(venteService.findAll()));
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> countVentes(){
        return ResponseEntity.ok(venteService.countAll());
    }

    @GetMapping(value = "/adresses")
    public ResponseEntity<List<Object>> getCommissionsAdresses(){
       // return ResponseEntity.ok(venteService.findCommissionAndAdresse());
       return ResponseEntity.ok(null);
    }
}

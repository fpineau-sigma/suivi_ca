package fr.sigma.ca.controller;

import com.google.common.collect.Lists;
import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.service.NegociateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/negociateurs")
public class NegociateurControler {

    @Autowired
    private NegociateurService negociateurService;

    @GetMapping()
    public ResponseEntity<List<NegociateurDTO>> getNegociateurs(){
        return ResponseEntity.ok(Lists.newArrayList(negociateurService.findAll()));
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> countNegociateurs(){
        return ResponseEntity.ok(negociateurService.countAll());
    }
}

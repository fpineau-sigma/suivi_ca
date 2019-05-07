package fr.sigma.ca.controller;

import com.google.common.collect.Lists;
import fr.sigma.ca.dto.OrigineDTO;
import fr.sigma.ca.service.OrigineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/origines")
public class OrigineControler {

    @Autowired
    private OrigineService origineService;

    @GetMapping()
    public ResponseEntity<List<OrigineDTO>> getOrigines(){
        return ResponseEntity.ok(Lists.newArrayList(origineService.findAll()));
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> countOrigines(){
        return ResponseEntity.ok(origineService.countAll());
    }
}

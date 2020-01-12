package fr.sigma.ca.controller;

import fr.sigma.ca.dto.VenteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping(path="/test")
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    public ResponseEntity<List<VenteDTO>> test(){
            log.info("Translated messages:");
            log.info("{}", messageSource.getMessage("l1",
                    null, Locale.GERMAN));
            log.info("{}", messageSource.getMessage("l1",
                    null, Locale.ENGLISH));
            log.info("Translated parameterized messages:");
            log.info("{}", messageSource.getMessage("l2",
                    new Object[] {"Paul Smith"}, Locale.GERMAN));
            log.info("{}", messageSource.getMessage("l2",
                    new Object[] {"Paul Smith"}, Locale.ENGLISH));
            return new ResponseEntity<List<VenteDTO>>(HttpStatus.OK);
    }

}

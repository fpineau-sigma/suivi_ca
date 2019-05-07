package fr.sigma.ca.controller;

import fr.sigma.ca.config.ConfigProperties;
import fr.sigma.ca.controller.fileService.XlsxFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FileControler {

    @Autowired
    public ConfigProperties config;

    @Autowired
    public XlsxFile xlsxFile;

    @GetMapping(value="/files")
    public ResponseEntity<Void> readXlxsFile(){
        log.info(" ******************* Debut du traitement *******************");

        // Lecture du fichier
        xlsxFile.lecture_fichier(config.getInputFileName(), config.getPassword());

        log.info(" ******************* Fin du traitement *******************");

        return ResponseEntity.ok().build();
    }
}

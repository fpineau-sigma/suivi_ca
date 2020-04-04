package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.fichierService.XlsxFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(path = "/api/fichiers")
@RequiredArgsConstructor
public class FichierController {

    public final XlsxFileService xlsxFileService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void importerDonnees(@RequestHeader("exercice") Long exerciceId,
        @RequestPart("fichier") MultipartFile fichier) {
        log.info(" ******************* Debut du traitement *******************");

        // Lecture du fichier
        xlsxFileService.lecture_fichier(fichier, exerciceId);

        log.info(" ******************* Fin du traitement *******************");
    }
}

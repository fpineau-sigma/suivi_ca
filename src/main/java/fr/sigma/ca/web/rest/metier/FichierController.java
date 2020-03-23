package fr.sigma.ca.web.rest.metier;

import fr.sigma.ca.service.metier.fichierService.XlsxFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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

    public final XlsxFile xlsxFile;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void importerDonnees(
        @RequestPart("fichier") MultipartFile fichier) {
        log.info(" ******************* Debut du traitement *******************");

        // Lecture du fichier
        xlsxFile.lecture_fichier(fichier);

        log.info(" ******************* Fin du traitement *******************");
    }
}

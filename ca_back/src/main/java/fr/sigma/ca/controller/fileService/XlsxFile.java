package fr.sigma.ca.controller.fileService;

import com.google.common.collect.Lists;
import fr.sigma.ca.domain.Adresse;
import fr.sigma.ca.dto.*;
import fr.sigma.ca.service.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
@Slf4j
public class XlsxFile implements IFileFacade {

    @Autowired
    public PersonneService personneService;

    @Autowired
    public NegociateurService negociateurService;

    @Autowired
    public OrigineService origineService;

    @Autowired
    public AdresseService adresseService;

    @Autowired
    public VenteService venteService;

    @Autowired
    public CommissionService commissionService;

    /**
     * Fonction de lecture du fichier Excel
     *
     * @param fileName
     * @return
     */

    public String lecture_fichier(String fileName, String password) {

        List<String> messages = new ArrayList<>();

        // Recuperer la liste des Personnes
        List<PersonneDTO> personnesDTOs = Lists.newArrayList(personneService.findAll());
        // Recuperer la liste des Adresses
        List<AdresseDTO> adressesDTOS = Lists.newArrayList(adresseService.findAll());
        // Recuperer la liste des negociateurs
        List<NegociateurDTO> negociateursDTOs = Lists.newArrayList(negociateurService.findAll());
        // Recuperer la liste des Origine
        List<OrigineDTO> originesDTOs = Lists.newArrayList(origineService.findAll());

        try {
            Path path = Paths.get("src/main/resources/" + fileName);
            InputStream fileStream = new FileInputStream(path.toAbsolutePath().toString());
            InputStream streamToAnalyze = fileStream;
            if (!StringUtils.isEmpty(password)) {

                POIFSFileSystem fs = new POIFSFileSystem(fileStream);
                EncryptionInfo info = new EncryptionInfo(fs);
                Decryptor d = Decryptor.getInstance(info);
                d.verifyPassword(password);
                streamToAnalyze = d.getDataStream(fs);

            }

            XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(streamToAnalyze);
            XSSFSheet ws = workbook.getSheetAt(workbook.getActiveSheetIndex());
            messages.add("Onglet : " + ws.getSheetName());

            // Parcours de l'ensemble des lignes
            for (int rowNum = 1; rowNum <= ws.getLastRowNum(); rowNum++) {
                final XSSFRow row = ws.getRow(rowNum);
                VenteDTO venteDTO = new VenteDTO();
                for (int colNum = row.getFirstCellNum(); colNum < row.getLastCellNum(); colNum++) {
                    String value;
                    switch (colNum) {
                        case (EnumTypeColonne.VENDEURS):
                            value = row.getCell(colNum).getStringCellValue().trim();
                            Arrays.asList(value.split("/")).stream().forEach(val-> {
                                if (!StringUtils.isEmpty(val)) {
                                    PersonneDTO personneDTO = enregistrerPersonne(personnesDTOs, val);
                                    venteDTO.getVendeurs().add(personneDTO);
                                }
                            });
                            break;
                        case (EnumTypeColonne.ACQUEREURS):
                            value = row.getCell(colNum).getStringCellValue().trim();
                            Arrays.asList(value.split("/")).stream().forEach(val-> {
                                if (!StringUtils.isEmpty(val)) {
                                    PersonneDTO personneDTO = enregistrerPersonne(personnesDTOs, val);
                                    venteDTO.getAcquereurs().add(personneDTO);
                                }
                            });
                            break;
                        case (EnumTypeColonne.ADRESSE):
                            if (!StringUtils.isEmpty(value = row.getCell(colNum).getStringCellValue().trim())) {
                                AdresseDTO adresseDTO = enregistrerAdresse(adressesDTOS, value);
                                venteDTO.setAdresse(adresseDTO);
                            }
                            break;
                        // colonne honoraire TTC
                        case (EnumTypeColonne.HONORAIRES_TTC):
                            venteDTO.setHonorairesTTC(new BigDecimal(row.getCell(colNum).getNumericCellValue()));
                            break;
                        // colonne honoraire HT
                        case (EnumTypeColonne.HONORAIRES_HT):
                            venteDTO.setHonorairesHT(new BigDecimal(row.getCell(colNum).getNumericCellValue()));
                            break;
                        case (EnumTypeColonne.NEGOS_ENTREE):
                            value = row.getCell(colNum).getStringCellValue().trim();
                            enregistrerCommissions(value,negociateursDTOs).stream().forEach(x -> venteDTO.getCommissionsEntree().add(x));
                            break;
                        case (EnumTypeColonne.NEGOS_SORTIE):
                            value = row.getCell(colNum).getStringCellValue().trim();
                            enregistrerCommissions(value,negociateursDTOs).stream().forEach(x -> venteDTO.getCommissionsSortie().add(x));
                            break;
                        case (EnumTypeColonne.ORIGINE):
                            if (!StringUtils.isEmpty(value = row.getCell(colNum).getStringCellValue().trim())) {
                                OrigineDTO origineDTO = enregistrerOrigine(originesDTOs, value);
                                venteDTO.setOrigine(origineDTO);
                            }
                            break;
                        case (EnumTypeColonne.DATE):
                            venteDTO.setDateVente(row.getCell(colNum).getDateCellValue());
                            break;
                    }
                }
                // Parcours des commissions pour calculs le montant des commissions
                venteDTO.getCommissionsEntree().stream().forEach(x-> {
                    x.setMontantHT(venteDTO.getHonorairesHT().multiply(x.getPourcentage()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                    x.setDateVente(venteDTO.getDateVente());
                    commissionService.enregistrerCommission(x);
                });
                venteDTO.getCommissionsSortie().stream().forEach(x-> {
                    x.setMontantHT(venteDTO.getHonorairesHT().multiply(x.getPourcentage()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                    x.setDateVente(venteDTO.getDateVente());
                    commissionService.enregistrerCommission(x);
                });
                venteService.enregistrerVente(venteDTO);
            }
        } catch (IOException i) {
            log.error("Problème de format de fichier : " + i);
        } catch (Exception e) {
            log.error("Problème lors du traitement : " + e);
        }
        return String.join("\n", messages);
    }


    /**
     * Permet l'enregistrement des commissions
     * @param value
     * @param negociateursDTOs
     * @return
     */
    private List<CommissionDTO> enregistrerCommissions (String value, List<NegociateurDTO> negociateursDTOs){
        List<CommissionDTO> commissionDTOS = new ArrayList<>();
        Arrays.asList(value.split("-")).stream().forEach(val-> {
            if (!StringUtils.isEmpty(val)) {
                // Si pourcentage commission spécifié alors on le récupère
                BigDecimal pourcentage = new BigDecimal(50);
                if (val.length() > 3){
                    pourcentage = new BigDecimal(val.substring(3,5));
                    val = val.substring(0,3);
                }
                NegociateurDTO negociateurDTO = enregistrerNegociateur(negociateursDTOs, val);
                CommissionDTO commissionDTO = new CommissionDTO(negociateurDTO, pourcentage, null, null);
                commissionDTOS.add(commissionDTO);
            }
        });
        // Mis à jour des pourcentages et enregistrement des valeurs
        Integer size = commissionDTOS.size();
        commissionDTOS.stream().forEach(x -> {
            if (x.getPourcentage().compareTo(new BigDecimal(50)) == 0){
                x.setPourcentage(x.getPourcentage().divide(new BigDecimal(size)));
            }
        });
        return commissionDTOS;
    }

    /**
     * Enregistrement des personnes
     * @param personnesDTOs
     * @param value
     */
    private PersonneDTO enregistrerPersonne(List<PersonneDTO> personnesDTOs, String value){
        PersonneDTO personneDTO = new PersonneDTO(value, null);
        int index = personnesDTOs.indexOf(personneDTO);
        if (index == -1) {
            personnesDTOs.add(personneDTO);
            personneService.enregistrerPersonne(personneDTO);
        }else{
            return personnesDTOs.get(index);
        }
        return personneDTO;
    }

    /**
     * Enregistrement des adresses
     * @param adressesDTOs
     * @param value
     */
    private AdresseDTO enregistrerAdresse(List<AdresseDTO> adressesDTOs, String value){
        // Récupération des informations liées à l'adresse
        String[] elements = value.split(",");
        Integer numeroVoie = null;
        String nomVoie = null;
        if (elements.length > 1){
            numeroVoie = Integer.valueOf(elements[0]);
            nomVoie = elements[1].trim();
        }else{
            nomVoie = elements[0].trim();
        }

        AdresseDTO adresseDTO = new AdresseDTO(numeroVoie, nomVoie, null, null);
        int index = adressesDTOs.indexOf(adresseDTO);
        if (index == -1) {
            adressesDTOs.add(adresseDTO);
            adresseService.enregistrerAdresse(adresseDTO);
        }else{
            return adressesDTOs.get(index);
        }
        return adresseDTO;
    }

    /**
     * Enregistrement des negociateurs
     * @param negociateursDTOs
     * @param value
     */
    private NegociateurDTO enregistrerNegociateur(List<NegociateurDTO> negociateursDTOs, String value){
        NegociateurDTO negociateurDTO = new NegociateurDTO(null,null,value);
        int index = negociateursDTOs.indexOf(negociateurDTO);
        if (index == -1) {
            negociateursDTOs.add(negociateurDTO);
            negociateurService.enregistrerNegociateur(negociateurDTO);
        }else{
            return negociateursDTOs.get(index);
        }
        return negociateurDTO;
    }

    /**
     * Enregistrement des origines
     * @param originesDTOs
     * @param value
     */
    private OrigineDTO enregistrerOrigine(List<OrigineDTO> originesDTOs, String value){
        OrigineDTO origineDTO = new OrigineDTO(value);
        int index = originesDTOs.indexOf(origineDTO);
        if (index == -1) {
            originesDTOs.add(origineDTO);
            origineService.enregistrerOrigine(origineDTO);
        }else{
            return originesDTOs.get(index);
        }
        return origineDTO;
    }
}

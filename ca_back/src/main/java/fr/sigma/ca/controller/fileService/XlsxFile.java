package fr.sigma.ca.controller.fileService;

import fr.sigma.ca.dto.AdresseDTO;
import fr.sigma.ca.dto.CommissionDTO;
import fr.sigma.ca.dto.NegociateurDTO;
import fr.sigma.ca.dto.PersonneDTO;
import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.entite.Origine;
import fr.sigma.ca.service.AdresseService;
import fr.sigma.ca.service.CommissionService;
import fr.sigma.ca.service.NegociateurService;
import fr.sigma.ca.service.PersonneService;
import fr.sigma.ca.service.VenteService;
import fr.sigma.ca.service.mapper.AdresseMapper;
import fr.sigma.ca.service.mapper.CommissionMapper;
import fr.sigma.ca.service.mapper.NegociateurMapper;
import fr.sigma.ca.service.mapper.PersonneMapper;
import fr.sigma.ca.service.mapper.VenteMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@Slf4j
@RequiredArgsConstructor
public class XlsxFile implements IFileFacade {

  private final PersonneService personneService;
  private final NegociateurService negociateurService;
  private final AdresseService adresseService;
  private final VenteService venteService;
  private final CommissionService commissionService;

  private final NegociateurMapper negociateurMapper;
  private final AdresseMapper adresseMapper;
  private final PersonneMapper personneMapper;
  private final VenteMapper venteMapper;
  private final CommissionMapper commissionMapper;


  /**
   * Fonction de lecture du fichier Excel
   *
   * @param fileName
   * @return
   */

  public String lecture_fichier(String fileName, String password) {

    List<String> messages = new ArrayList<>();

    // Recuperer la liste des Personnes
    Collection<PersonneDTO> personnesDTOs = personneMapper.toDto(personneService.lister());
    // Recuperer la liste des Adresses
    Collection<AdresseDTO> adressesDTOS = adresseMapper.toDto(adresseService.lister());
    // Recuperer la liste des negociateurs
    Collection<NegociateurDTO> negociateursDTOs = negociateurMapper
        .toDto(negociateurService.lister());

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
        VenteDTO venteDTO = VenteDTO.builder().vendeurs(new ArrayList<>())
            .acquereurs(new ArrayList<>()).build();
        ArrayList<CommissionDTO> commissionsEntree = new ArrayList<>();
        ArrayList<CommissionDTO> commissionsSortie = new ArrayList<>();

        for (int colNum = row.getFirstCellNum(); colNum < row.getLastCellNum(); colNum++) {
          String value;
          switch (colNum) {
            case (EnumTypeColonne.VENDEURS):
              value = row.getCell(colNum).getStringCellValue().trim();
              Arrays.stream(value.split("/")).forEach(val -> {
                if (!StringUtils.isEmpty(val)) {
                  venteDTO.getVendeurs().add(enregistrerPersonne(personnesDTOs, val));
                }
              });
              break;
            case (EnumTypeColonne.ACQUEREURS):
              value = row.getCell(colNum).getStringCellValue().trim();
              Arrays.stream(value.split("/")).forEach(val -> {
                if (!StringUtils.isEmpty(val)) {
                  venteDTO.getAcquereurs().add(enregistrerPersonne(personnesDTOs, val));
                }
              });
              break;
            case (EnumTypeColonne.ADRESSE):
              if (!StringUtils.isEmpty(value = row.getCell(colNum).getStringCellValue().trim())) {
                venteDTO.setAdresse(deduireAdresse(adressesDTOS, value));
              }
              break;
            // colonne honoraire TTC
            case (EnumTypeColonne.HONORAIRES_TTC):
              venteDTO
                  .setHonorairesTTC(BigDecimal.valueOf(row.getCell(colNum).getNumericCellValue()));
              break;
            // colonne honoraire HT
            case (EnumTypeColonne.HONORAIRES_HT):
              venteDTO
                  .setHonorairesHT(BigDecimal.valueOf(row.getCell(colNum).getNumericCellValue()));
              break;
            case (EnumTypeColonne.NEGOS_ENTREE):
              value = row.getCell(colNum).getStringCellValue().trim();
              commissionsEntree.addAll(enregistrerCommissions(value, negociateursDTOs));
              break;
            case (EnumTypeColonne.NEGOS_SORTIE):
              value = row.getCell(colNum).getStringCellValue().trim();
              commissionsSortie.addAll(enregistrerCommissions(value, negociateursDTOs));
              break;
            case (EnumTypeColonne.ORIGINE):
              if (!StringUtils.isEmpty(value = row.getCell(colNum).getStringCellValue().trim())) {
                venteDTO.setOrigine(Origine.valueOf(value));
              }
              break;
            case (EnumTypeColonne.DATE):
              venteDTO.setDateVente(row.getCell(colNum).getDateCellValue());
              break;
            default:
              break;
          }
        }
        venteDTO.setCommissionsEntree(new ArrayList<>());
        venteDTO.setCommissionsSortie(new ArrayList<>());
        // Parcours des commissions pour calculs le montant des commissions
        commissionsEntree.forEach(commissionEntreeDTO -> {
          commissionEntreeDTO.setMontantHT(
              venteDTO.getHonorairesHT().multiply(commissionEntreeDTO.getPourcentage())
                  .divide(new BigDecimal(100))
                  .setScale(2, BigDecimal.ROUND_HALF_EVEN));
          venteDTO.getCommissionsEntree().add(commissionMapper.toDto(commissionService
              .enregistrerCommission(commissionMapper.toEntity(commissionEntreeDTO))));
        });
        commissionsSortie.forEach(commisionSortieDTO -> {
          commisionSortieDTO.setMontantHT(
              venteDTO.getHonorairesHT().multiply(commisionSortieDTO.getPourcentage())
                  .divide(new BigDecimal(100))
                  .setScale(2, BigDecimal.ROUND_HALF_EVEN));
          venteDTO.getCommissionsSortie().add(commissionMapper.toDto(commissionService
              .enregistrerCommission(commissionMapper.toEntity(commisionSortieDTO))));
        });
        venteService.enregistrerVente(venteMapper.toEntity(venteDTO));
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
   *
   * @param value
   * @param negociateursDTOs
   * @return
   */
  private List<CommissionDTO> enregistrerCommissions(String value,
      Collection<NegociateurDTO> negociateursDTOs) {
    List<CommissionDTO> commissionDTOS = new ArrayList<>();
    Arrays.asList(value.split("-")).stream().forEach(val -> {
      if (!StringUtils.isEmpty(val)) {
        // Si pourcentage commission spécifié alors on le récupère
        BigDecimal pourcentage = new BigDecimal(50);
        if (val.length() > 3) {
          pourcentage = new BigDecimal(val.substring(3, 5));
          val = val.substring(0, 3);
        }
        NegociateurDTO negociateurDTO = enregistrerNegociateur(negociateursDTOs, val);
        CommissionDTO commissionDTO = CommissionDTO.builder().negociateur(negociateurDTO)
            .pourcentage(pourcentage).build();
        commissionDTOS.add(commissionDTO);
      }
    });
    // Mis à jour des pourcentages et enregistrement des valeurs
    int size = commissionDTOS.size();
    commissionDTOS.forEach(x -> {
      if (x.getPourcentage().compareTo(new BigDecimal(50)) == 0) {
        x.setPourcentage(x.getPourcentage().divide(BigDecimal.valueOf(size), BigDecimal.ROUND_UP));
      }
    });
    return commissionDTOS;
  }

  /**
   * Enregistrement des personnes
   *
   * @param personnesDTO
   * @param nom
   */
  private PersonneDTO enregistrerPersonne(Collection<PersonneDTO> personnesDTO, String nom) {
    Optional<PersonneDTO> personneBDD = personnesDTO.stream()
        .filter(personneDTO -> personneDTO.getNom().equals(nom)).findFirst();
    if (personneBDD.isPresent()) {
      return personneBDD.get();
    } else {
      PersonneDTO nouvellePersonne = PersonneDTO.builder().nom(nom).build();
      nouvellePersonne = personneMapper
          .toDto(personneService.creer(personneMapper.toEntity(nouvellePersonne)));
      personnesDTO.add(nouvellePersonne);
      return nouvellePersonne;
    }
  }

  /**
   * Enregistrement des adresses
   *
   * @param adressesDTO
   * @param value
   */
  private AdresseDTO deduireAdresse(Collection<AdresseDTO> adressesDTO, String value) {
    // Récupération des informations liées à l'adresse
    String[] elements = value.split(",");
    Integer numeroVoie = null;
    String nomVoie = null;
    if (elements.length > 1) {
      numeroVoie = Integer.valueOf(elements[0]);
      nomVoie = elements[1].trim();
    } else {
      nomVoie = elements[0].trim();
    }

    return enregistrerAdresse(adressesDTO, numeroVoie, nomVoie);
  }

  private AdresseDTO enregistrerAdresse(Collection<AdresseDTO> adressesDTO,
      final Integer numeroVoie,
      final String nomVoie) {
    Optional<AdresseDTO> adresseBDD = adressesDTO.stream()
        .filter(adresseDTO -> adresseDTO.getNomVoie().equals(nomVoie) && adresseDTO.getNumeroVoie()
            .equals(numeroVoie)).findFirst();
    if (adresseBDD.isPresent()) {
      return adresseBDD.get();
    } else {
      AdresseDTO nouvelleAdresse = AdresseDTO.builder().nomVoie(nomVoie).numeroVoie(numeroVoie)
          .build();
      nouvelleAdresse = adresseMapper
          .toDto(adresseService.creer(adresseMapper.toEntity(nouvelleAdresse)));
      adressesDTO.add(nouvelleAdresse);
      return nouvelleAdresse;
    }
  }

  /**
   * Enregistrement des negociateurs
   *
   * @param negociateursDTO
   * @param nomCourt
   */
  private NegociateurDTO enregistrerNegociateur(Collection<NegociateurDTO> negociateursDTO,
      String nomCourt) {
    Optional<NegociateurDTO> negociateurBDD = negociateursDTO.stream()
        .filter(negociateurDTO -> negociateurDTO.getNomCourt().equals(nomCourt)).findFirst();
    if (negociateurBDD.isPresent()) {
      return negociateurBDD.get();
    } else {
      NegociateurDTO nouveauNego = NegociateurDTO.builder().nomCourt(nomCourt).build();
      nouveauNego = negociateurMapper
          .toDto(negociateurService.creer(negociateurMapper.toEntity(nouveauNego)));
      negociateursDTO.add(nouveauNego);
      return nouveauNego;
    }
  }
}
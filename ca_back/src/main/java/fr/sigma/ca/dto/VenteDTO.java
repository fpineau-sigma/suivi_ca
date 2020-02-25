package fr.sigma.ca.dto;

import fr.sigma.ca.integration.persistence.DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class VenteDTO extends DTO {

  private LocalDate dateActeAuthentique;
  private LocalDate dateCompromis;
  private String numeroFacture;
  private String typeBien;
  private Collection<CommissionDTO> commissionsEntree;
  private Collection<CommissionDTO> commissionsSortie;
  private OrigineDTO origine;
  private BigDecimal honorairesTTC;
  private BigDecimal honorairesHT;
  private AdresseDTO adresse;
  private Collection<PersonneDTO> vendeurs;
  private Collection<PersonneDTO> acquereurs;

  @Builder
  public VenteDTO(
      Long id,
      LocalDate dateActeAuthentique,
      LocalDate dateCompromis,
      String numeroFacture,
      String typeBien,
      Collection<CommissionDTO> commissionsEntree,
      Collection<CommissionDTO> commissionsSortie,
      OrigineDTO origine,
      BigDecimal honorairesTTC,
      BigDecimal honorairesHT,
      AdresseDTO adresse,
      Collection<PersonneDTO> vendeurs,
      Collection<PersonneDTO> acquereurs
  ) {
    super(id);
    this.dateActeAuthentique = dateActeAuthentique;
    this.dateCompromis = dateCompromis;
    this.numeroFacture = numeroFacture;
    this.typeBien = typeBien;
    this.commissionsEntree = commissionsEntree;
    this.commissionsSortie = commissionsSortie;
    this.origine = origine;
    this.honorairesTTC = honorairesTTC;
    this.honorairesHT = honorairesHT;
    this.adresse = adresse;
    this.vendeurs = vendeurs;
    this.acquereurs = acquereurs;
  }
}

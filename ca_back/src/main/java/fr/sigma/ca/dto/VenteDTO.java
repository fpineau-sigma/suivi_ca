package fr.sigma.ca.dto;

import fr.sigma.ca.entite.Origine;
import fr.sigma.ca.integration.persistence.DTO;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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

  private Date dateVente;
  private Collection<CommissionDTO> commissionsEntree;
  private Collection<CommissionDTO> commissionsSortie;
  private Origine origine;
  private BigDecimal honorairesTTC;
  private BigDecimal honorairesHT;
  private AdresseDTO adresse;
  private Collection<PersonneDTO> vendeurs;
  private Collection<PersonneDTO> acquereurs;

  @Builder
  public VenteDTO(
      Long id,
      Date dateVente,
      Collection<CommissionDTO> commissionsEntree,
      Collection<CommissionDTO> commissionsSortie,
      Origine origine,
      BigDecimal honorairesTTC,
      BigDecimal honorairesHT,
      AdresseDTO adresse,
      Collection<PersonneDTO> vendeurs,
      Collection<PersonneDTO> acquereurs
  ) {
    super(id);
    this.dateVente = dateVente;
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

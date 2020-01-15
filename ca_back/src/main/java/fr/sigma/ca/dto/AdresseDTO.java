package fr.sigma.ca.dto;

import fr.sigma.ca.integration.persistence.DTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class AdresseDTO extends DTO {

  private Integer numeroVoie;
  private String nomVoie;
  private Integer codePostal;
  private String ville;

  @Builder
  public AdresseDTO(Long id, Integer numeroVoie, String nomVoie, Integer codePostal, String ville) {
    super(id);
    this.numeroVoie = numeroVoie;
    this.nomVoie = nomVoie;
    this.codePostal = codePostal;
    this.ville = ville;
  }
}

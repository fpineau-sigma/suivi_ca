package fr.sigma.ca.dto;

import fr.sigma.ca.integration.persistence.DTO;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CommissionDTO extends DTO {

  private NegociateurDTO negociateur;
  private BigDecimal pourcentage;
  private BigDecimal montantHT;

  @Builder
  public CommissionDTO(Long id, NegociateurDTO negociateur, BigDecimal pourcentage,
      BigDecimal montantHT) {
    super(id);
    this.negociateur = negociateur;
    this.pourcentage = pourcentage;
    this.montantHT = montantHT;
  }

}
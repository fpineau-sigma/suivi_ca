package fr.sigma.ca.service.metier.dto;

import fr.sigma.ca.entite.metier.TypeOrigine;
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
public class MontantTypeOrigineDTO extends DTO {

    private BigDecimal montant;
    private TypeOrigine typeOrigine;

    @Builder
    public MontantTypeOrigineDTO(Long id, BigDecimal montant, TypeOrigine typeOrigine) {
        super(id);
        this.montant = montant;
        this.typeOrigine = typeOrigine;
    }
}

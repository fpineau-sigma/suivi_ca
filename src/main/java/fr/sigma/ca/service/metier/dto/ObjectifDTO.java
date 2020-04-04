package fr.sigma.ca.service.metier.dto;

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
public class ObjectifDTO extends DTO {

    private Long exerciceId;
    private BigDecimal montant;
    private BigDecimal realise;
    private BigDecimal restant;

    @Builder
    public ObjectifDTO(Long id, Long exerciceId, BigDecimal montant, BigDecimal realise) {
        super(id);
        this.exerciceId = exerciceId;
        this.montant = montant;
        this.realise = realise;
        this.restant = montant != null ? montant.subtract(realise) : BigDecimal.ZERO;
    }
}

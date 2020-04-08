package fr.sigma.ca.service.metier.dto;

import fr.sigma.ca.integration.persistence.DTO;
import java.math.BigDecimal;
import java.util.Collection;
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
    private Collection<MontantTypeOrigineDTO> montantTypeOrigines;
    private Integer nombreVentes;
    private NegociateurDTOLeger negociateur;

    @Builder
    public ObjectifDTO(Long id, Long exerciceId, NegociateurDTOLeger negociateur,
        BigDecimal montant, BigDecimal restant,
        BigDecimal realise,
        Collection<MontantTypeOrigineDTO> montantTypeOrigines,
        Integer nombreVentes) {
        super(id);
        this.exerciceId = exerciceId;
        this.montant = montant;
        this.realise = realise;
        this.restant = restant;
        this.montantTypeOrigines = montantTypeOrigines;
        this.nombreVentes = nombreVentes;
        this.negociateur = negociateur;
    }
}

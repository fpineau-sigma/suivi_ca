package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.EntiteAgence;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Objectif extends EntiteAgence {

    @Basic
    @Column(name = "exercice_id", nullable = false)
    private Long exerciceId;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montant;

    private BigDecimal realise;

    private BigDecimal restant;
}

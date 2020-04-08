package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.EntiteAgence;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "objectif")
public class MontantTypeOrigine extends EntiteAgence {

    @Basic
    @Column(name = "exercice_id", nullable = false)
    private Long exerciceId;

    private BigDecimal montant;

    @NotNull
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TypeOrigine typeOrigine;

    @ManyToOne(optional = false)
    @JoinColumn(name = "objectif_id")
    private Objectif objectif;
}

package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.EntiteAgence;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Commission extends EntiteAgence {

    @Basic
    @Column(name = "exercice_id", nullable = false)
    private Long exerciceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "negociateur_id", nullable = false)
    @NotNull
    private Negociateur negociateur;

    private BigDecimal pourcentage;

    private BigDecimal montantHT;
}

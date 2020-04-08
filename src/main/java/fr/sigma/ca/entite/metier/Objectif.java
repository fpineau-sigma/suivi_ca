package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.EntiteAgence;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "negociateur")
public class Objectif extends EntiteAgence {

    @Basic
    @Column(name = "exercice_id", nullable = false)
    private Long exerciceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "negociateur_id")
    private Negociateur negociateur;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montant;

    private BigDecimal realise;

    private BigDecimal restant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objectif")
    @EqualsAndHashCode.Exclude
    private Collection<MontantTypeOrigine> montantTypeOrigines;

    private Integer nombreVentes;

    @PrePersist
    public void prePersist() {
        montant = montant == null ? BigDecimal.ZERO : montant;
        realise = realise == null ? BigDecimal.ZERO : realise;
        restant = restant == null ? BigDecimal.ZERO : restant;
        nombreVentes = nombreVentes == null ? 0 : nombreVentes;
    }

    @PreUpdate
    public void preUpdate() {
        // Mise à jour du montant restant
        if (null != realise && null != montant) {
            restant = montant.subtract(realise);
        }
    }
}

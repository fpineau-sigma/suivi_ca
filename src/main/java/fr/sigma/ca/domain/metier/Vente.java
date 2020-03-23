package fr.sigma.ca.domain.metier;

import fr.sigma.ca.integration.persistence.Entite;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Vente extends Entite {

    @NotNull
    @Column(nullable = false)
    private LocalDate dateActeAuthentique;

    private LocalDate dateCompromis;

    @ManyToOne
    @JoinColumn(name = "type_de_bien_id")
    private TypeDeBien typeDeBien;

    private String numeroFacture;

    @OneToMany
    private Collection<Commission> commissionsEntree;

    @OneToMany
    private Collection<Commission> commissionsSortie;

    @NotNull
    @ManyToOne(optional = false)
    private Origine origine;

    private BigDecimal honorairesTTC;

    private BigDecimal honorairesHT;

    @ManyToOne(optional = false)
    @NotNull
    private Adresse adresse;

    @ManyToMany
    @NotNull
    @Column(nullable = false)
    private Collection<Personne> vendeurs;

    @ManyToMany
    @NotNull
    @Column(nullable = false)
    private Collection<Personne> acquereurs;
}

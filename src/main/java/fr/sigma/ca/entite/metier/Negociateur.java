package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.EntiteAgence;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@ToString
public class Negociateur extends EntiteAgence {

    private String nom;
    private String prenom;

    @NotNull
    @Column(nullable = false, unique = true)
    private String nomCourt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "negociateur")
    @EqualsAndHashCode.Exclude
    private Collection<Objectif> objectifs;

    private Boolean actif;
}

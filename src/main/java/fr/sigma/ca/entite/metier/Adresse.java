package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.EntiteAgence;
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
public class Adresse extends EntiteAgence {

    private Integer numeroVoie;

    @NotNull
    @Column(nullable = false)
    private String nomVoie;

    private Integer codePostal;
    private String ville;
}

package fr.sigma.ca.entite.metier;

import fr.sigma.ca.integration.persistence.Entite;
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
public class Exercice extends Entite {

    @NotNull
    @Column(nullable = false, unique = true)
    private String libelle;
}

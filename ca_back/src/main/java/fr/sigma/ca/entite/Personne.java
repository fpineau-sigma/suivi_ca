package fr.sigma.ca.entite;

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
public class Personne extends Entite {

  @NotNull
  @Column(nullable = false)
  private String nom;

  private String prenom;
}

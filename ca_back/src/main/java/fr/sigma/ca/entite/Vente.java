package fr.sigma.ca.entite;

import fr.sigma.ca.integration.persistence.Entite;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  private Timestamp dateVente;

  @OneToMany
  private Collection<Commission> commissionsEntree;

  @OneToMany
  private Collection<Commission> commissionsSortie;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
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

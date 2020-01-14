package fr.sigma.ca.entite;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vente {

  private static final long serialVersionUID = 1L;

  @Id
  private UUID id;

  private Timestamp dateVente;

  @OneToMany
  private List<Commission> commissionsEntree;

  @OneToMany
  private List<Commission> commissionsSortie;

  @OneToOne
  private Origine origine;

  private BigDecimal honorairesTTC;

  private BigDecimal honorairesHT;

  @OneToOne
  private Adresse adresse;

  @ManyToMany
  private List<Personne> vendeurs;

  @ManyToMany
  private List<Personne> acquereurs;

}

package fr.sigma.ca.entite;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Commission {

  private static final long serialVersionUID = 1L;

  @Id
  private UUID id;

  @OneToOne
  private Negociateur negociateur;

  private BigDecimal pourcentage;

  private BigDecimal montantHT;

  private Timestamp dateVente;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "VENTE_ID")
  private Vente vente;
}
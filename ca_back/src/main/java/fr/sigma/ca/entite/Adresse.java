package fr.sigma.ca.entite;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Adresse {

  private static final long serialVersionUID = 1L;

  @Id
  private UUID id;

  private Integer numero;

  private String nomVoie;

  private Integer codePostal;

  private String ville;
}

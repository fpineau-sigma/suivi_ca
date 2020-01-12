package fr.sigma.ca.integration.persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Entite {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  public static Long getIdOf(Entite entite) {
    if (null != entite) {
      return entite.getId();
    } else {
      return null;
    }
  }
}

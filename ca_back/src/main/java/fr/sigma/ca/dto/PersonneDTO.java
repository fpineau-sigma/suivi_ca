package fr.sigma.ca.dto;

import fr.sigma.ca.integration.persistence.DTO;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonneDTO extends DTO {

  private String nom;
  private String prenom;

  @Builder
  public PersonneDTO(Long id, String nom, String prenom) {
    super(id);
    this.nom = nom;
    this.prenom = prenom;
  }

  /**
   * Two users are equal if their firstName, lastName and email address is same.
   */
  @Override
  public boolean equals(Object obj) {
    if (null == obj) {
      return false;
    }
    return Objects.equals(this.nom, ((PersonneDTO) obj).nom) && Objects
        .equals(this.prenom, ((PersonneDTO) obj).prenom);
  }
}

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
public class NegociateurDTO extends DTO {

  private String nom;
  private String prenom;
  private String nomCourt;

  @Builder
  public NegociateurDTO(Long id, String nom, String prenom, String nomCourt) {
    super(id);
    this.nom = nom;
    this.prenom = prenom;
    this.nomCourt = nomCourt;
  }

  /**
   * Two users are equal if their firstName, lastName and email address is same.
   */
  @Override
  public boolean equals(Object obj) {
    if (null == obj) {
      return false;
    }
    return Objects.equals(this.nomCourt, ((NegociateurDTO) obj).nomCourt);
  }
}

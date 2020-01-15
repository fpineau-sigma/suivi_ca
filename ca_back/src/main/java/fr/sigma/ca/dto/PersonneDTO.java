package fr.sigma.ca.dto;

import fr.sigma.ca.integration.persistence.DTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PersonneDTO extends DTO {

  private String nom;
  private String prenom;

  @Builder
  public PersonneDTO(Long id, String nom, String prenom) {
    super(id);
    this.nom = nom;
    this.prenom = prenom;
  }
}

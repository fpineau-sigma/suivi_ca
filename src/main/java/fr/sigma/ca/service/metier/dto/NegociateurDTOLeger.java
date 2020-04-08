package fr.sigma.ca.service.metier.dto;

import fr.sigma.ca.integration.persistence.DTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NegociateurDTOLeger extends DTO {

    private String nom;
    private String prenom;
    private String nomCourt;
    private Boolean actif;

    @Builder
    public NegociateurDTOLeger(Long id, String nom, String prenom, String nomCourt, Boolean actif) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.nomCourt = nomCourt;
        this.actif = actif;
    }
}

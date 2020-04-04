package fr.sigma.ca.service.metier.dto;

import fr.sigma.ca.integration.persistence.DTO;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NegociateurDTO extends DTO {

    private String nom;
    private String prenom;
    private String nomCourt;
    private Boolean actif;
    private List<ObjectifDTO> objectifs;

    @Builder
    public NegociateurDTO(Long id, String nom, String prenom, String nomCourt, Boolean actif,
        List<ObjectifDTO> objectifs) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.nomCourt = nomCourt;
        this.actif = actif;
        this.objectifs = objectifs;
    }
}

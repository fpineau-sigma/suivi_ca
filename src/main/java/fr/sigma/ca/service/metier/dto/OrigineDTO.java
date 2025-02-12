package fr.sigma.ca.service.metier.dto;

import fr.sigma.ca.entite.metier.TypeOrigine;
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
public class OrigineDTO extends DTO {

    private String libelle;
    private TypeOrigine typeOrigine;

    @Builder
    public OrigineDTO(Long id, String libelle, TypeOrigine typeOrigine) {
        super(id);
        this.libelle = libelle;
        this.typeOrigine = typeOrigine;
    }
}

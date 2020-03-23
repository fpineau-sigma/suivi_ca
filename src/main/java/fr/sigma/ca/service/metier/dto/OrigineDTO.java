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
public class OrigineDTO extends DTO {

    private String libelle;

    @Builder
    public OrigineDTO(Long id, String libelle) {
        super(id);
        this.libelle = libelle;
    }
}

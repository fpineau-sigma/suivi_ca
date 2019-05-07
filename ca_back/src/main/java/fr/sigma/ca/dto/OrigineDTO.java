package fr.sigma.ca.dto;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrigineDTO {

    private UUID id;

    private String libelle;

    public OrigineDTO(String libelle) {
        this.id = UUID.randomUUID();
        this.libelle = libelle;
    }

    /**
     * Two users are equal if their firstName, lastName and email address is same.
     */
    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.libelle, ((OrigineDTO) obj).libelle) ;
    }
}

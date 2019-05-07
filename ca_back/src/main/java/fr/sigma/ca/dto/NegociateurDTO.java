package fr.sigma.ca.dto;

import lombok.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NegociateurDTO extends PersonneDTO{

    private String nomCourt;

    @Builder
    public NegociateurDTO(String nom, String prenom, String nomCourt) {
        super(nom, prenom);
        this.nomCourt = nomCourt;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && Objects.equals(this.nomCourt,((NegociateurDTO) obj).nomCourt);
    }
}

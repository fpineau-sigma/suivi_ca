package fr.sigma.ca.dto;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdresseDTO {

    private UUID id;

    private Integer numero;

    private String nomVoie;

    private Integer codePostal;

    private String ville;

    public AdresseDTO(Integer numero, String nomVoie, Integer codePostal, String ville) {
        this.id = UUID.randomUUID();
        this.numero = numero;
        this.nomVoie = nomVoie;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    @Override
    public boolean equals(Object obj) {
        return  Objects.equals(this.numero,((AdresseDTO) obj).numero) && Objects.equals(this.nomVoie,((AdresseDTO) obj).nomVoie)
                && Objects.equals(this.codePostal,((AdresseDTO) obj).codePostal) && Objects.equals(this.ville,((AdresseDTO) obj).ville);
    }
}

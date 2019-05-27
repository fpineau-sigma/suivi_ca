package fr.sigma.ca.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@ToString(of= {"nom","prenom"})
public class PersonneDTO {

    private UUID id;

    private String nom;

    private String prenom;


    public PersonneDTO(String nom, String prenom) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Two users are equal if their firstName, lastName and email address is same.
     */
    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.nom, ((PersonneDTO) obj).nom) && Objects.equals(this.prenom, ((PersonneDTO) obj).prenom) ;
    }
}

package fr.sigma.ca.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Negociateur {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @OneToOne
    private Personne personne;

    private String nomCourt;
}

package fr.sigma.ca.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Adresse {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private Integer numero;

    private String nomVoie;

    private Integer codePostal;

    private String ville;
}

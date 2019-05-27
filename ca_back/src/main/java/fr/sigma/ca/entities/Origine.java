package fr.sigma.ca.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;


@Entity
@Data
public class Origine {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String libelle;

}

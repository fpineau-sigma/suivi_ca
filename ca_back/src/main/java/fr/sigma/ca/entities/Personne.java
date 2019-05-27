package fr.sigma.ca.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Personne {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String nom;

    private String prenom;

    private Timestamp dateImport;

    @PrePersist
    public void beforeCreate(){
        this.dateImport = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void beforeUpdate(){
        this.dateImport = new Timestamp(System.currentTimeMillis());
    }
}

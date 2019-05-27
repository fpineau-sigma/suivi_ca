package fr.sigma.ca.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
public class Vente {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private Timestamp dateVente;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Commission> commissionsEntree;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Commission> commissionsSortie;

    @OneToOne
    private Origine origine;

    private BigDecimal honorairesTTC;

    private BigDecimal honorairesHT;

    @OneToOne
    private Adresse adresse;

    @ManyToMany
    private Collection<Personne> vendeurs;

    @ManyToMany
    private Collection<Personne> acquereurs;

}

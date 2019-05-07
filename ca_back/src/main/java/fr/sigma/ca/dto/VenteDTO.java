package fr.sigma.ca.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class VenteDTO {

    private UUID id;

    private Date dateVente;

    private List<CommissionDTO> commissionsEntree;

    private List<CommissionDTO> commissionsSortie;

    private OrigineDTO origine;

    private BigDecimal honorairesTTC;

    private BigDecimal honorairesHT;

    private AdresseDTO adresse;

    private List<PersonneDTO> vendeurs;

    private List<PersonneDTO> acquereurs;


    public VenteDTO() {
        this.id = UUID.randomUUID();
        this.vendeurs = new ArrayList<>();
        this.acquereurs = new ArrayList<>();
        this.commissionsEntree = new ArrayList<>();
        this.commissionsSortie = new ArrayList<>();
    }
}

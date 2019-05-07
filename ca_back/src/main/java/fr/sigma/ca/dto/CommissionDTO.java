package fr.sigma.ca.dto;

import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommissionDTO {

    private UUID id;

    private NegociateurDTO negociateur;

    private BigDecimal pourcentage;

    private BigDecimal montantHT;

    private Date dateVente;

    public CommissionDTO(NegociateurDTO negociateur, BigDecimal pourcentage, BigDecimal montantHT, Date dateVente) {
        this.id = UUID.randomUUID();
        this.negociateur = negociateur;
        this.pourcentage = pourcentage;
        this.montantHT = montantHT;
        this.dateVente = dateVente;
    }
}
package fr.sigma.ca.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Commission {

        private static final long serialVersionUID = 1L;

        @Id
        private UUID id;

        @OneToOne
        private Negociateur negociateur;

        private BigDecimal pourcentage;

        private BigDecimal montantHT;

        private Timestamp dateVente;
}
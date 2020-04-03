package fr.sigma.ca.service.metier.dto;

import fr.sigma.ca.entite.metier.VenteStatut;
import fr.sigma.ca.integration.persistence.DTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class VenteDTO extends DTO {

    private Long exerciceId;
    private LocalDate dateActeAuthentique;
    private LocalDate dateCompromis;
    private String numeroFacture;
    private TypeDeBienDTO typeDeBien;
    private Collection<CommissionDTO> commissionsEntree;
    private Collection<CommissionDTO> commissionsSortie;
    private OrigineDTO origine;
    private BigDecimal honorairesTTC;
    private BigDecimal honorairesHT;
    private AdresseDTO adresse;
    private Collection<PersonneDTO> vendeurs;
    private Collection<PersonneDTO> acquereurs;
    private VenteStatut statut;

    @Builder
    public VenteDTO(
        Long id,
        Long exerciceId,
        LocalDate dateActeAuthentique,
        LocalDate dateCompromis,
        String numeroFacture,
        TypeDeBienDTO typeDeBien,
        Collection<CommissionDTO> commissionsEntree,
        Collection<CommissionDTO> commissionsSortie,
        OrigineDTO origine,
        BigDecimal honorairesTTC,
        BigDecimal honorairesHT,
        AdresseDTO adresse,
        Collection<PersonneDTO> vendeurs,
        Collection<PersonneDTO> acquereurs,
        VenteStatut statut
    ) {
        super(id);
        this.exerciceId = exerciceId;
        this.dateActeAuthentique = dateActeAuthentique;
        this.dateCompromis = dateCompromis;
        this.numeroFacture = numeroFacture;
        this.typeDeBien = typeDeBien;
        this.commissionsEntree = commissionsEntree;
        this.commissionsSortie = commissionsSortie;
        this.origine = origine;
        this.honorairesTTC = honorairesTTC;
        this.honorairesHT = honorairesHT;
        this.adresse = adresse;
        this.vendeurs = vendeurs;
        this.acquereurs = acquereurs;
        this.statut = statut;
    }
}

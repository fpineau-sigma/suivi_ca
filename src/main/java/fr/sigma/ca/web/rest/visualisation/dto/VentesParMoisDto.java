package fr.sigma.ca.web.rest.visualisation.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VentesParMoisDto {

    private Integer mois;
    private Long nombreVente;

    @QueryProjection
    public VentesParMoisDto(Integer mois, Long nombreVente) {
        this.mois = mois;
        this.nombreVente = nombreVente;
    }
}

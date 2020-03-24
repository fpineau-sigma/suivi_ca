package fr.sigma.ca.web.rest.metier.dto;

import fr.sigma.ca.service.metier.dto.NegociateurDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriteresRechercheCommissionDto {

    private NegociateurDTO negociateur;
}

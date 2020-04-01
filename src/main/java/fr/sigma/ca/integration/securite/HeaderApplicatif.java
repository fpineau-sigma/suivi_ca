package fr.sigma.ca.integration.securite;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum HeaderApplicatif {
    AGENCE("agence"),
    EXERCICE("exercice");

    private String cle;
}

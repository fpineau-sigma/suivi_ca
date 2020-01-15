package fr.sigma.ca.entite;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Origine {
  C("Copro"),
  C_SYNERGIE("Copro_Synergie"),
  D("Diffu"),
  D_SYNERGIE("Diffu_Synergie"),
  G("Gestion"),
  G_UFF("Gestion_UFF");

  private String libelle;
}

import {Adresse} from "./adresse.model";
import {Personne} from "./personne.model";
import {Commission} from "./commission.model";

export class Vente {
  dateVente: Date;
  commissionsEntree: Commission[];
  commissionsSortie: Commission [];
  origine: string;
  honorairesTTC: number;
  honorairesHT: number;
  adresse: Adresse;
  vendeurs: Personne [];
  acquereurs: Personne [];
}

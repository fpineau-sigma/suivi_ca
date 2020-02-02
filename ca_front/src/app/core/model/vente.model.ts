import {Adresse} from "./adresse.model";
import {Personne} from "./personne.model";
import {Commission} from "./commission.model";

export class Vente {
  id?: number;
  dateCompromis?: Date;
  dateActeAuthentique?: Date;
  typeBien?: string;
  numeroFacture?: string;
  commissionsEntree?: Commission[] = [];
  commissionsSortie?: Commission[] = [];
  origine?: string;
  honorairesTTC?: number;
  honorairesHT?: number;
  adresse?: Adresse;
  vendeurs?: Personne[] = [];
  acquereurs?: Personne[] = [];
}

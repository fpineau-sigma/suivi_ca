import {Commission} from './commission.model';
import {Adresse} from './adresse.model';
import {Personne} from './personne.model';
import {Origine} from './origine.model';
import {TypeDeBien} from './typedebien.model';


export class Vente {
  id?: number;
  dateCompromis?: Date;
  dateActeAuthentique?: Date;
  typeBien?: TypeDeBien;
  numeroFacture?: string;
  commissionsEntree?: Commission[] = [];
  commissionsSortie?: Commission[] = [];
  origine?: Origine;
  honorairesTTC?: number;
  honorairesHT?: number;
  adresse?: Adresse = new Adresse();
  vendeurs?: Personne[] = [];
  acquereurs?: Personne[] = [];
}

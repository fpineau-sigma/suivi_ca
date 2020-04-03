import {Objectif} from 'app/core/model/metier/objectif.model';

export class Negociateur {
  public id?: number;
  public nomCourt?: string;
  public nom?: string;
  public prenom?: string;
  public actif?: boolean;
  public objectifs?: Objectif[] = [];
}

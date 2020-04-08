import {Negociateur} from 'app/core/model/metier/negociateur.model';
import {MontantTypeOrigine} from 'app/core/model/metier/montant.type.origine.model';

export class Objectif {
  public id?: number;
  public montant?: number;
  public realise?: number;
  public exerciceId?: number;
  public restant?: number;
  public negociateur?: Negociateur;
  public montantTypeOrigines?: MontantTypeOrigine[] = [];
}

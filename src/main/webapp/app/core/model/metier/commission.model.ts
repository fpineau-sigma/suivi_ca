import { Negociateur } from './negociateur.model';

export class Commission {
  public id?: number;
  public pourcentage?: number;
  public montantHT?: number;
  public dateVente?: Date;
  public negociateur?: Negociateur;
}

import { Negociateur } from './negociateur.model';

export class Commission {
    public pourcentage?: number;
    public montantHT?: number;
    public dateVente?: Date;
    public negociateur?: Negociateur;
}

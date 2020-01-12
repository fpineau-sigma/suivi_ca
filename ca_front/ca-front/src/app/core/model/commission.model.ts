import { Negociateur } from './negociateur.model';

export interface Commission {

    pourcentage: number;
    montantHT: number;
    dateVente: Date;
    negociateur: Negociateur;
}

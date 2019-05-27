import { Negociateur } from './negociateur';

export interface Commission {

    pourcentage: number;
    montantHT: number;
    dateVente: Date;
    negociateur: Negociateur;
}

import { Negociateur } from '../../negociateur/service/negociateur';

export interface Commission{

    pourcentage: number;
    montantHT: number;
    dateVente: Date;
    negociateur: Negociateur;
}

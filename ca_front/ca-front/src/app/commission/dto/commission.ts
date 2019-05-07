import { Negociateur } from '../../negociateur/dto/negociateur';

export class Commission{

    pourcentage: number;
    montantHT: number;
    dateVente: Date;
    negociateur: Negociateur;

    constructor(
      pourcentage: number,
      montantHT: number,
      dateVente: Date = new Date(),
      negociateur: Negociateur
    ){
        this.pourcentage = pourcentage;
        this.montantHT = montantHT;
        this.dateVente = dateVente;
        this.negociateur = negociateur;
    }
}

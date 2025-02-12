import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {ColumnMode} from '@swimlane/ngx-datatable';
import {Page} from 'app/core/model/pagination/page.model';
import {Tri} from 'app/core/model/pagination/tri.model';
import {ITEMS_PER_PAGE} from 'app/shared/constants/pagination.constants';
import {preparerTriPourServeur} from 'app/shared/util/utilitaire-datatable';
import {map} from 'rxjs/operators';
import {HttpResponse} from '@angular/common/http';
import {Vente} from 'app/core/model/metier/vente.model';
import {CriteresRechercheVente} from 'app/core/model/criteres.recherche/criteresRechercheVente.model';
import {VentesService} from 'app/core/service/metier/ventes.service';
import {Negociateur} from 'app/core/model/metier/negociateur.model';

@Component({
  selector: 'jhi-tableau-ventes-negociateur',
  templateUrl: './tableau-ventes-negociateur.component.html'
})
export class TableauVentesNegociateurComponent implements OnInit, OnDestroy {
  // Liste subscription
  private subscriptions: Subscription[] = [];

  @Input() negociateur: Negociateur;

  // Gestion de la pagination
  public page = new Page<Vente[]>();
  private offset: number;
  private tri: Tri[];

  public estChargementEnCours = false;
  private criteresRechercheVente: CriteresRechercheVente;

  constructor(private ventesService: VentesService) {
  }

  ngOnInit(): void {
    this.criteresRechercheVente = new CriteresRechercheVente();
    this.criteresRechercheVente.negociateur = this.negociateur;
    this.rechercherInterne(null).subscribe((pagedData: Page<Vente[]>) => {
      this.page = pagedData;
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      if (subscription) {
        subscription.unsubscribe();
      }
    });
  }

  /**
   * Expose l'enum ColumnMode pour le composant ngx-datatable
   */
  public get ColumnMode(): typeof ColumnMode {
    return ColumnMode;
  }


  /**
   * Fonction qui gère le changement de page
   * @param pageInfo
   */
  public changerPage(pageInfo): void {
    this.page.number = pageInfo.offset;
    this.offset = pageInfo.offset;
    this.rechercherInterne(null).subscribe((pagedData: Page<Vente[]>) => {
      this.page = pagedData;
    });
  }

  /**
   * Fonction de recherche des dossiers de Ventes
   * @param dossierPaieCritere
   * @param tri
   */
  public rechercherInterne(tri?: Tri[]): Observable<Page<Vente[]>> {
    this.estChargementEnCours = true;
    this.tri = tri;

    return this.ventesService
    .lister(this.criteresRechercheVente, {
      page: this.page.number,
      size: ITEMS_PER_PAGE,
      sort: tri ? preparerTriPourServeur(tri) : null
    })
    .pipe(
      map((page: HttpResponse<Page<Vente[]>>) => {
        this.estChargementEnCours = false;

        return page.body;
      })
    );
  }

  // Fonctions d'affichages des données dans le tableau
  public afficherVendeurs(row: Vente): string {
    const vendeurs = [];
    row.vendeurs.forEach(vendeur => vendeurs.push([vendeur.nom, vendeur.prenom].filter(Boolean).join(" ")));
    return vendeurs.filter(Boolean).join('/');
  }

  public afficherAcquereurs(row: Vente): string {
    const acquereurs = [];
    row.acquereurs.forEach(acquereur => acquereurs.push([acquereur.nom, acquereur.prenom].filter(Boolean).join(" ")));
    return acquereurs.filter(Boolean).join('/');
  }

  public afficherAdresse(row: Vente): string {
    return row.adresse == null ? "" : [row.adresse.numeroVoie, row.adresse.nomVoie, row.adresse.codePostal, row.adresse.ville].filter(Boolean).join(" ");
  }

  public afficherNegociateursEntree(row: Vente): string {
    const negociateurs = [];
    row.commissionsEntree.forEach(com => negociateurs.push(com.negociateur.nomCourt));
    return negociateurs.filter(Boolean).join(' ');
  }

  public afficherNegociateursSortie(row: Vente): string {
    const negociateurs = [];
    row.commissionsSortie.forEach(com => negociateurs.push(com.negociateur.nomCourt));
    return negociateurs.filter(Boolean).join(' ');
  }

  public afficherHonoraires(row: Vente): string {
    let honoraires = 0;
    row.commissionsSortie.filter(com => com.negociateur.id === this.negociateur.id).forEach(comNego => honoraires += comNego.montantHT);
    row.commissionsEntree.filter(com => com.negociateur.id === this.negociateur.id).forEach(comNego => honoraires += comNego.montantHT);
    return honoraires.toString();
  }
}

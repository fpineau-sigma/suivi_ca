import { Component, OnDestroy, OnInit } from '@angular/core';
import { faEdit } from '@fortawesome/free-solid-svg-icons/faEdit';
import { Vente } from '../../core/model/metier/vente.model';
import { Observable, Subscription } from 'rxjs';
import { VentesService } from '../../core/service/metier/ventes.service';
import { Router } from '@angular/router';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { Mode } from '../../core/model/metier/mode.enum';
import { Page } from 'app/core/model/pagination/page.model';
import { Tri } from 'app/core/model/pagination/tri.model';
import { CriteresRechercheVente } from 'app/core/model/criteres.recherche/criteresRechercheVente.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { preparerTriPourServeur } from 'app/shared/util/utilitaire-datatable';
import { map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-ventes',
  templateUrl: './liste-ventes.component.html'
})
export class ListeVentesComponent implements OnInit, OnDestroy {
  public faEdit = faEdit;

  // Liste subscription
  private subscriptions: Subscription[] = [];

  // Gestion de la pagination
  public page = new Page<Vente[]>();
  private offset: number;
  private tri: Tri[];

  public estChargementEnCours = false;
  private criteresRechercheVente: CriteresRechercheVente;

  constructor(private ventesService: VentesService, private router: Router) {}

  ngOnInit(): void {
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

  public creer(): void {
    this.router.navigate([`/ventes/editer-vente/${Mode.CREATION}`]);
  }

  public modifierVente(vente: Vente): void {
    this.router.navigate([`/ventes/editer-vente/${Mode.EDITION}/${vente.id}`]);
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
  public rechercherInterne(criteresRechercheVente: CriteresRechercheVente, tri?: Tri[]): Observable<Page<Vente[]>> {
    this.criteresRechercheVente = criteresRechercheVente;
    this.estChargementEnCours = true;
    this.tri = tri;

    return this.ventesService
      .lister(criteresRechercheVente, {
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
}

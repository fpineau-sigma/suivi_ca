import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { CommissionsService } from '../../../core/service/metier/commissions.service';
import { Page } from 'app/core/model/pagination/page.model';
import { Tri } from 'app/core/model/pagination/tri.model';
import { CriteresRechercheCommission } from 'app/core/model/criteres.recherche/criteresRechercheCommission.model';
import { Commission } from 'app/core/model/metier/commission.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { preparerTriPourServeur } from 'app/shared/util/utilitaire-datatable';
import { map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-commissions',
  templateUrl: './liste-commissions.component.html'
})
export class ListeCommissionsComponent implements OnInit, OnDestroy {
  // Liste subscription
  private subscriptions: Subscription[] = [];

  // Gestion de la pagination
  public page = new Page<Commission[]>();
  private offset: number;
  private tri: Tri[];

  public estChargementEnCours = false;
  private criteresRechercheCommission: CriteresRechercheCommission;

  constructor(private commissionsService: CommissionsService) {}

  ngOnInit(): void {
    this.rechercherInterne(null).subscribe((pagedData: Page<Commission[]>) => {
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
    this.rechercherInterne(null).subscribe((pagedData: Page<Commission[]>) => {
      this.page = pagedData;
    });
  }

  /**
   * Fonction de recherche des dossiers de Commissions
   * @param dossierPaieCritere
   * @param tri
   */
  public rechercherInterne(criteresRechercheCommission: CriteresRechercheCommission, tri?: Tri[]): Observable<Page<Commission[]>> {
    this.criteresRechercheCommission = criteresRechercheCommission;
    this.estChargementEnCours = true;
    this.tri = tri;

    return this.commissionsService
      .lister(criteresRechercheCommission, {
        page: this.page.number,
        size: ITEMS_PER_PAGE,
        sort: tri ? preparerTriPourServeur(tri) : null
      })
      .pipe(
        map((page: HttpResponse<Page<Commission[]>>) => {
          this.estChargementEnCours = false;

          return page.body;
        })
      );
  }
}

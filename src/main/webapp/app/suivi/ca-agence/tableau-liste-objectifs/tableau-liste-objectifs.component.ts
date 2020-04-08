import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {ColumnMode} from '@swimlane/ngx-datatable';
import {ObjectifService} from 'app/core/service/metier/objectifs.service';
import {Objectif} from 'app/core/model/metier/objectif.model';
import {Agence} from 'app/core/model/metier/agence.model';
import {TypeOrigine} from 'app/core/model/metier/type.origine.enum';

@Component({
  selector: 'jhi-tableau-liste-objectifs',
  templateUrl: './tableau-liste-objectifs.component.html'
})
export class TableauListeObjectifsComponent implements OnInit, OnDestroy {
  // Liste subscription
  private subscriptions: Subscription[] = [];

  public objectifs: Objectif[] = [];

  constructor(private objectifService: ObjectifService) {
  }

  ngOnInit(): void {
    this.subscriptions.push(
      this.objectifService.lister().subscribe((res: Agence[]) => {
        this.objectifs = res;
      })
    );
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

  public get TypeOrigines(): typeof TypeOrigine {
    return TypeOrigine;
  }

  public afficherMontantTypeOrigine(objectif: Objectif, typeOrigine: any): number {
    if (objectif.montantTypeOrigines != null) {
      const montant = objectif.montantTypeOrigines.find(value => value.typeOrigine === typeOrigine);
      if (montant != null) {
        return montant.montant
      }
    }
    return 0;
  }
}

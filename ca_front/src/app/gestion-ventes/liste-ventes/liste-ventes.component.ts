import {Component, OnDestroy, OnInit} from '@angular/core';
import {faEdit} from '@fortawesome/free-solid-svg-icons/faEdit';
import {Vente} from '../../core/model/vente.model';
import {Subscription} from 'rxjs';
import {VentesService} from '../../core/service/ventes.service';
import {Router} from '@angular/router';
import {ColumnMode} from '@swimlane/ngx-datatable';
import {Mode} from '../../core/model/mode.enum';


@Component({
  selector: 'app-ventes',
  templateUrl: './liste-ventes.component.html'
})
export class ListeVentesComponent implements OnInit, OnDestroy {

  public faEdit = faEdit;
  public ventes: Vente[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  constructor(
    private readonly ventesService: VentesService,
    private readonly router: Router
  ) {
  }


  ngOnInit(): void {
    this.subscriptions.push(this.ventesService.lister().subscribe((res: Vente[]) => {
      this.ventes = res;
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      if (!!subscription) {
        subscription.unsubscribe();
      }
    });
  }

  /**
   * Expose l'enum ColumnMode pour le composant ngx-datatable
   */
  public get ColumnMode() {
    return ColumnMode;
  }

  public creer(): void {
    this.router.navigate([`/ventes/editer-vente/${Mode.CREATION}`]);
  }

  public modifierVente(vente: Vente): void {
    this.router.navigate([`/ventes/editer-vente/${Mode.EDITION}/${vente.id}`]);
  }
}

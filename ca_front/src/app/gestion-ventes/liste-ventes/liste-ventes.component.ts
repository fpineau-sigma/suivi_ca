import {Component, OnDestroy, OnInit} from '@angular/core';
import {VentesService} from "../../core/service/ventes.service";
import {Negociateur} from "../../core/model/negociateur.model";
import {Vente} from "../../core/model/vente.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-ventes',
  templateUrl: './liste-ventes.component.html'
})
export class ListeVentesComponent implements OnInit, OnDestroy {

  public ventes: Vente[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  constructor(
    private readonly  ventesService: VentesService
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

}

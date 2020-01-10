import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {NegociateursService} from "../../core/services/negociateurs.service";
import {Negociateur} from "../../core/model/negociateur.model";

@Component({
  selector: 'app-negociateurs',
  templateUrl: './liste-negociateurs.component.html'
})
export class ListeNegociateursComponent implements OnInit, OnDestroy{

  public negociateurs : Negociateur[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  constructor(
    private readonly negociateursService: NegociateursService,
  ) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.negociateursService.lister().subscribe((res: Negociateur[]) => {
      this.negociateurs = res;
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

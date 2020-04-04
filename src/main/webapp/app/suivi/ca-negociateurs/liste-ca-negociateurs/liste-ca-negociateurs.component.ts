import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {NegociateursService} from 'app/core/service/metier/negociateurs.service';
import {Negociateur} from 'app/core/model/metier/negociateur.model';
import {ClesSessionStorage} from 'app/shared/constants/storage.constants';

@Component({
  selector: 'jhi-suivi-ca-negociateurs',
  templateUrl: './liste-ca-negociateurs.component.html'
})
export class ListeCaNegociateursComponent implements OnInit, OnDestroy {
  // Liste subscription
  private subscriptions: Subscription[] = [];
  public negociateurs: Negociateur[] = [];
  private exerciceId: number;

  constructor(private negociateursService: NegociateursService) {
  }

  ngOnInit(): void {
    this.exerciceId = +sessionStorage.getItem(ClesSessionStorage.EXERCICE);
    this.subscriptions.push(
      this.negociateursService.lister().subscribe((res: Negociateur[]) => {
        this.negociateurs = res;
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

  public afficherObjectif(negociateur: Negociateur): string {
    const objectiEnCours = negociateur.objectifs.find(value => value.exerciceId === this.exerciceId);
    return objectiEnCours == null ? "" : "Objectif annuel = " + objectiEnCours.montant + " € " + " montant réalisé = " + objectiEnCours.realise + " €";
  }
}

import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Adresse} from '../../core/model/adresse.model';
import {ActivatedRoute} from '@angular/router';
import {ChampVendeurAcquereur} from './acquereurs-vendeurs/editer-vente-acquereurs-vendeurs.component';
import {Mode} from '../../core/model/mode.enum';
import {Vente} from '../../core/model/vente.model';
import {VentesService} from '../../core/service/ventes.service';
import {ChampCommission} from './commissions/editer-vente-commissions.component';
import {Subscription} from 'rxjs';


@Component({
  selector: 'app-vente',
  templateUrl: './editer-vente.component.html'
})
export class EditerVenteComponent implements OnInit, OnDestroy {

  @Input() public vente: Vente;
  @Input() public mode: Mode;

  public venteEditer: Vente;
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  public id: number;

  constructor(private readonly route: ActivatedRoute,
              private readonly ventesService: VentesService) {
  }

  public ngOnInit(): void {

    this.subscriptions.push(this.route.params.subscribe((params) => {
      if (!!params.mode) {
        this.mode = Mode[Mode[params.mode]];
      }
      if (!!params.id) {
        this.id = params.id;
        this.subscriptions.push(this.ventesService.lire(this.id).subscribe((vente: Vente) => {
          this.venteEditer = vente;
        }));
      } else {
        this.venteEditer = new Vente();
        this.venteEditer.adresse = new Adresse();
        this.venteEditer.acquereurs = [];
        this.venteEditer.vendeurs = [];

      }
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      if (!!subscription) {
        subscription.unsubscribe();
      }
    });
  }

  get ModeEnum() {
    return Mode;
  }

  get ChampVendeur() {
    return ChampVendeurAcquereur;
  }

  get ChampCommission() {
    return ChampCommission;
  }
}

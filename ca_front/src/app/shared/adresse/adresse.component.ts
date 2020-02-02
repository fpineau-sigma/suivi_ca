import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Adresse} from "../../core/model/adresse.model";
import {Mode} from "../../core/model/mode.enum";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-adresse',
  templateUrl: './adresse.component.html'
})
export class AdresseComponent implements OnInit, OnDestroy {

  @Input() public adresse: Adresse;
  @Input() public mode: Mode;

  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  public adresseEditer: Adresse;

  constructor() {
  }

  public ngOnInit(): void {
    this.adresseEditer = this.adresse;
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
}

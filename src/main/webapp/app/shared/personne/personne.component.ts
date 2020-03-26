import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Personne} from '../../core/model/metier/personne.model';
import {Mode} from '../../core/model/metier/mode.enum';
import {Subscription} from 'rxjs';

@Component({
  selector: 'jhi-personne',
  templateUrl: './personne.component.html'
})
export class PersonneComponent implements OnInit, OnDestroy {

  @Input() public personne: Personne;
  @Input() public mode: Mode;

  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  constructor() {
  }

  public ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      if (subscription) {
        subscription.unsubscribe();
      }
    });
  }
}

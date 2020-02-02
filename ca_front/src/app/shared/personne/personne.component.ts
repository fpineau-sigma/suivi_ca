import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Personne} from '../../core/model/personne.model';
import {Mode} from '../../core/model/mode.enum';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-personne',
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
      if (!!subscription) {
        subscription.unsubscribe();
      }
    });
  }

  get ModeEnum() {
    return Mode;
  }
}

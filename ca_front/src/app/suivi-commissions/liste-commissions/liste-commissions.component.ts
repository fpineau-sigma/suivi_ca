import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {ColumnMode} from '@swimlane/ngx-datatable';
import {CommissionsService} from '../../core/service/commissions.service';
import {Commission} from '../../core/model/commission.model';


@Component({
  selector: 'app-commissions',
  templateUrl: './liste-commissions.component.html'
})
export class ListeCommissionsComponent implements OnInit, OnDestroy {

  public commissions: Commission[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  constructor(
    private readonly commissionsService: CommissionsService,
  ) {
  }


  ngOnInit(): void {
    this.subscriptions.push(this.commissionsService.lister().subscribe((res: Commission[]) => {
      this.commissions = res;
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
}

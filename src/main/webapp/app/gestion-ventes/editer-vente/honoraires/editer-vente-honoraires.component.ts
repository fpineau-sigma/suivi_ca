import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ControlContainer, NgForm} from '@angular/forms';
import {Mode} from '../../../core/model/mode.enum';
import {Vente} from '../../../core/model/vente.model';
import {OriginesService} from '../../../core/service/application/origines.service';
import {Subscription} from 'rxjs';
import {Origine} from '../../../core/model/origine.model';
import {TypeDeBiensService} from '../../../core/service/application/typedebiens.service';
import {TypeDeBien} from '../../../core/model/typedebien.model';


@Component({
  selector: 'jhi-editer-vente-honoraires',
  templateUrl: './editer-vente-honoraires.component.html',
  viewProviders: [{provide: ControlContainer, useExisting: NgForm}]
})
export class EditerVenteHonorairesComponent implements OnInit, OnDestroy {

  @Input() public mode: Mode;
  @Input() public vente: Vente;

  public origines: Origine[];
  public typeDeBiens: TypeDeBien[];

  // Liste subscription
  private readonly subscriptions: Subscription[] = [];

  constructor(public form: NgForm,
              private originesService: OriginesService,
              private typeDeBiensService: TypeDeBiensService) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.originesService.lister().subscribe((res: Origine[]) => {
        this.origines = res;
      }),
      this.typeDeBiensService.lister().subscribe((res: TypeDeBien[]) => {
        this.typeDeBiens = res;
      }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      if (subscription) {
        subscription.unsubscribe();
      }
    });
  }

  get Mode(): typeof Mode {
    return Mode;
  }

}
import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {
  ControlContainer,
  FormBuilder,
  FormGroup,
  FormGroupDirective,
  Validators
} from '@angular/forms';
import {Mode} from '../../../core/model/metier/mode.enum';
import {Vente} from '../../../core/model/metier/vente.model';
import {OriginesService} from '../../../core/service/metier/origines.service';
import {Subscription} from 'rxjs';
import {Origine} from '../../../core/model/metier/origine.model';
import {TypeDeBiensService} from '../../../core/service/metier/typedebiens.service';
import {TypeDeBien} from '../../../core/model/metier/typedebien.model';
import {BsLocaleService} from 'ngx-bootstrap';

@Component({
  selector: 'jhi-editer-vente-honoraires',
  templateUrl: './editer-vente-honoraires.component.html',
  viewProviders: [{provide: ControlContainer, useExisting: FormGroupDirective}]
})
export class EditerVenteHonorairesComponent implements OnInit, OnDestroy {
  @Input() public mode: Mode;
  @Input() public vente: Vente;

  public origines: Origine[];
  public typeDeBiens: TypeDeBien[];

  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  public venteHonorairesForm: FormGroup;

  constructor(
    private venteForm: FormGroupDirective,
    private formBuilder: FormBuilder,
    private localeService: BsLocaleService,
    private originesService: OriginesService,
    private typeDeBiensService: TypeDeBiensService
  ) {
  }

  ngOnInit(): void {
    this.localeService.use('fr');
    this.venteHonorairesForm = this.venteForm.form;
    this.venteHonorairesForm.addControl(
      '_honoraires',
      this.formBuilder.group({
        venteDateVenteCompromis: [''],
        venteOrigine: ['', Validators.required],
        venteHonorairesTTC: ['', Validators.pattern('[0-9]+(\\.[0-9][0-9]?)?')],
        venteHonorairesHT: ['', Validators.pattern('[0-9]+(\\.[0-9][0-9]?)?')],
        venteDateActeAuthentique: ['', Validators.required],
        venteNumeroFacture: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        venteTypeBien: ['']
      })
    );
    this.subscriptions.push(
      this.originesService.lister().subscribe((res: Origine[]) => {
        this.origines = res;
      }),
      this.typeDeBiensService.lister().subscribe((res: TypeDeBien[]) => {
        this.typeDeBiens = res;
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

  get Mode(): typeof Mode {
    return Mode;
  }
}

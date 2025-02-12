import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ControlContainer, FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { NegociateursService } from '../../../core/service/metier/negociateurs.service';
import { Mode } from '../../../core/model/metier/mode.enum';
import { Vente } from '../../../core/model/metier/vente.model';
import { Commission } from '../../../core/model/metier/commission.model';
import { Negociateur } from '../../../core/model/metier/negociateur.model';
import { Subscription } from 'rxjs';
import { faTrash } from '@fortawesome/free-solid-svg-icons/faTrash';
import { faEdit } from '@fortawesome/free-solid-svg-icons/faEdit';
import { ColumnMode } from '@swimlane/ngx-datatable';

export enum ChampCommission {
  ENTREE,
  SORTIE
}

@Component({
  selector: 'jhi-editer-vente-commissions',
  templateUrl: './editer-vente-commissions.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class EditerVenteCommissionsComponent implements OnInit, OnDestroy {
  @Input() public mode: Mode;
  @Input() public vente: Vente;
  @Input() public champ: ChampCommission;

  public commissions: Commission[] = [];
  public negociateurs: Negociateur[];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  public faTrash = faTrash;
  public faEdit = faEdit;

  public commissionEnEdition: Commission;
  private commissionSelectionnee: Commission;
  public editionEnCours = false;

  public venteCommissionForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private negociateursService: NegociateursService) {}

  ngOnInit(): void {
    this.venteCommissionForm = this.formBuilder.group({
      commissionNegociateur: ['', Validators.required],
      commissionPourcentage: ['', [Validators.required, Validators.pattern('^[0-9]*?')]],
      commissionMontantHt: ['', [Validators.required, Validators.pattern('[0-9]+(\\.[0-9][0-9]?)?')]]
    });
    this.subscriptions.push(
      this.negociateursService.lister().subscribe((res: Negociateur[]) => {
        this.negociateurs = res;
      })
    );
    if (this.champ === ChampCommission.ENTREE) {
      this.commissions = this.vente.commissionsEntree;
    } else {
      this.commissions = this.vente.commissionsSortie;
    }
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

  /**
   * Expose l'enum ColumnMode pour le composant ngx-datatable
   */
  public get ColumnMode(): typeof ColumnMode {
    return ColumnMode;
  }

  public supprimerCommission(commissionEditer: Commission): void {
    this.commissions = this.commissions.filter(commission => commission !== commissionEditer);
    this.commissions = [...this.commissions];
    this.miseAjourVente();
  }

  public editerCommission(commission: Commission): void {
    if (null === commission) {
      this.commissionEnEdition = Object.assign({}, new Commission());
    } else {
      this.commissionSelectionnee = commission;
      this.commissionEnEdition = Object.assign({}, commission);
    }
    this.editionEnCours = true;
    this.miseAjourVente();
  }

  public ajouterCommission(): void {
    this.commissionEnEdition.negociateur = this.venteCommissionForm.get(['commissionNegociateur'])!.value;
    this.commissionEnEdition.pourcentage = this.venteCommissionForm.get(['commissionPourcentage'])!.value;
    this.commissionEnEdition.montantHT = this.venteCommissionForm.get(['commissionMontantHt'])!.value;

    const index = this.commissions.indexOf(this.commissionSelectionnee);
    if (index === -1) {
      this.commissions = [...this.commissions, this.commissionEnEdition];
    } else {
      this.commissions[index] = this.commissionEnEdition;
      this.commissions = [...this.commissions];
    }
    this.commissionEnEdition = null;
    this.commissionSelectionnee = null;
    this.editionEnCours = false;
    this.miseAjourVente();
  }

  private miseAjourVente(): void {
    if (this.champ === ChampCommission.ENTREE) {
      this.vente.commissionsEntree = [...this.commissions];
    } else {
      this.vente.commissionsSortie = [...this.commissions];
    }
  }
}

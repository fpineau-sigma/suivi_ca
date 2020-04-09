import {Component, OnDestroy, OnInit} from '@angular/core';
import {Agence} from 'app/core/model/metier/agence.model';
import {Subscription} from 'rxjs';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {AgencesService} from 'app/core/service/metier/agences.service';
import {RefreshService} from 'app/core/service/refresh.service';
import {ToastService} from 'app/core/service/toast.service';
import {ConfigModalCommun} from 'app/core/configuration/config-modal-commun.class';
import {Mode} from 'app/core/model/metier/mode.enum';
import {EditerAgenceModalComponent} from '../editer-agence/editer-agence-modal.component';
import {Negociateur} from 'app/core/model/metier/negociateur.model';

@Component({
  selector: 'jhi-agences',
  templateUrl: './liste-agences.component.html'
})
export class ListeAgencesComponent implements OnInit, OnDestroy {
  public agences: Agence[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  private modalActive: BsModalRef | undefined;

  constructor(
    private agencesService: AgencesService,
    private toastService: ToastService,
    private modalService: BsModalService,
    private refreshService: RefreshService
  ) {
  }

  ngOnInit(): void {
    this.subscriptions.push(
      this.agencesService.lister().subscribe((res: Agence[]) => {
        this.agences = res;
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

  // Ouverture de la modal des négociateurs
  public ouvrirModalAgence(agence: Agence, agences: Agence[], mode: Mode): void {
    this.modalActive = this.modalService.show(
      EditerAgenceModalComponent,
      Object.assign(new ConfigModalCommun(), {
        class: 'modal-dialog-centered modal-lg',
        initialState: {
          mode,
          agences,
          agence
        }
      })
    );
    this.subscriptions.push(
      (this.modalActive.content as EditerAgenceModalComponent).fermer.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerAgenceModalComponent).annuler.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerAgenceModalComponent).enregistrer.subscribe((nego: Negociateur) => {
        this.agencesService.enregistrer(nego).subscribe(() => {
          this.toastService.success('gestion.agence.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      }),
      (this.modalActive.content as EditerAgenceModalComponent).modifier.subscribe((nego: Negociateur) => {
        this.agencesService.modifier(nego).subscribe(() => {
          this.toastService.success('gestion.agence.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      })
    );
  }

  // Fermeture de la modal
  public fermerModal(): void {
    if (this.modalActive) {
      this.modalActive.hide();
      this.modalActive = undefined;
    }
  }

  public creerAgence(): void {
    const agence = new Agence();
    this.ouvrirModalAgence(agence, this.agences, Mode.CREATION);
  }

  public modifierAgence(agence: Agence): void {
    this.ouvrirModalAgence(agence, this.agences, Mode.EDITION);
  }
}

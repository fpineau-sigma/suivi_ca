import {Component, OnDestroy, OnInit} from '@angular/core';
import {Origine} from '../../../core/model/metier/origine.model';
import {Subscription} from 'rxjs';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {OriginesService} from '../../../core/service/metier/origines.service';
import {RefreshService} from '../../../core/service/refresh.service';
import {ToastService} from '../../../core/service/toast.service';
import {ConfigModalCommun} from '../../../core/configuration/config-modal-commun.class';
import {Mode} from '../../../core/model/metier/mode.enum';
import {EditerOrigineModalComponent} from '../editer-origine/editer-origine-modal.component';
import {Negociateur} from 'app/core/model/metier/negociateur.model';


@Component({
  selector: 'jhi-origines',
  templateUrl: './liste-origines.component.html'
})
export class ListeOriginesComponent implements OnInit, OnDestroy {

  public origines: Origine[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  private modalActive: BsModalRef | undefined;

  constructor(
    private  originesService: OriginesService,
    private  toastService: ToastService,
    private  modalService: BsModalService,
    private  refreshService: RefreshService
  ) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.originesService.lister().subscribe((res: Origine[]) => {
      this.origines = res;
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      if (subscription) {
        subscription.unsubscribe();
      }
    });
  }

  // Ouverture de la modal des négociateurs
  public ouvrirModalOrigine(origine: Origine, mode: Mode): void {
    this.modalActive = this.modalService.show(EditerOrigineModalComponent, Object.assign(new ConfigModalCommun(), {
      class: 'modal-dialog-centered modal-lg',
      initialState: {
        mode,
        origine
      }
    }));
    this.subscriptions.push(
      (this.modalActive.content as EditerOrigineModalComponent).fermer.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerOrigineModalComponent).annuler.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerOrigineModalComponent).enregistrer.subscribe((nego: Negociateur) => {
        this.originesService.enregistrer(nego).subscribe(() => {
          this.toastService.success('gestion.origine.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      }),
      (this.modalActive.content as EditerOrigineModalComponent).modifier.subscribe((nego: Negociateur) => {
        this.originesService.modifier(nego).subscribe(() => {
          this.toastService.success('gestion.origine.action.success');
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

  public creerOrigine(): void {
    const origine = new Origine();
    this.ouvrirModalOrigine(origine, Mode.CREATION);
  }

  public modifierOrigine(origine: Origine): void {
    this.ouvrirModalOrigine(origine, Mode.EDITION);
  }
}

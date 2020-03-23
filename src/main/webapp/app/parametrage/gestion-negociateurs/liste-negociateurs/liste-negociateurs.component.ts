import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {NegociateursService} from 'app/core/service/application/negociateurs.service';
import {Negociateur} from 'app/core/model/negociateur.model';
import {ToastService} from 'app/core/service/toast.service';
import {RefreshService} from 'app/core/service/refresh.service';
import {Mode} from 'app/core/model/mode.enum';
import {EditerNegociateurModalComponent} from 'app/parametrage/gestion-negociateurs/editer-negociateur/editer-negociateur-modal.component';
import {ConfigModalCommun} from 'app/core/configuration/config-modal-commun.class';


@Component({
  selector: 'jhi-negociateurs',
  templateUrl: './liste-negociateurs.component.html'
})
export class ListeNegociateursComponent implements OnInit, OnDestroy {

  public negociateurs: Negociateur[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  private modalActive: BsModalRef | undefined;

  constructor(
    private negociateursService: NegociateursService,
    private toastService: ToastService,
    private modalService: BsModalService,
    private refreshService: RefreshService
  ) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.negociateursService.lister().subscribe((res: Negociateur[]) => {
      this.negociateurs = res;
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
  public ouvrirModalNegociateur(negociateur: Negociateur, mode: Mode): void {
    this.modalActive = this.modalService.show(EditerNegociateurModalComponent, Object.assign(new ConfigModalCommun(), {
      class: 'modal-dialog-centered modal-lg',
      initialState: {
        mode,
        negociateur
      }
    }));
    this.subscriptions.push(
      (this.modalActive.content as EditerNegociateurModalComponent).fermer.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerNegociateurModalComponent).annuler.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerNegociateurModalComponent).enregistrer.subscribe((nego: Negociateur) => {
        this.negociateursService.enregistrer(nego).subscribe(() => {
          this.toastService.success('gestion.negociateur.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      }),
      (this.modalActive.content as EditerNegociateurModalComponent).modifier.subscribe((nego: Negociateur) => {
        this.negociateursService.modifier(nego).subscribe(() => {
          this.toastService.success('gestion.negociateur.action.success');
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

  public creerNegociateur(): void {
    const negociateur = new Negociateur();
    this.ouvrirModalNegociateur(negociateur, Mode.CREATION);
  }

  public modifierNegociateur(negociateur: Negociateur): void {
    this.ouvrirModalNegociateur(negociateur, Mode.EDITION);
  }
}

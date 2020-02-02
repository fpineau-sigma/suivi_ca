import {Component, OnDestroy, OnInit} from '@angular/core';
import {Negociateur} from '../../core/model/negociateur.model';
import {Subscription} from 'rxjs';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {NegociateursService} from '../../core/service/negociateurs.service';
import {ToastService} from '../../core/service/toast.service';
import {RefreshService} from '../../core/service/refresh.service';
import {EditerNegociateurModalComponent} from '../editer-negociateur/editer-negociateur-modal.component';
import {ConfigModalCommun} from '../../core/configuration/config-modal-commun.class';
import {Mode} from '../../core/model/mode.enum';


@Component({
  selector: 'app-negociateurs',
  templateUrl: './liste-negociateurs.component.html'
})
export class ListeNegociateursComponent implements OnInit, OnDestroy {

  public negociateurs: Negociateur[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  private modalActive: BsModalRef;

  constructor(
    private readonly negociateursService: NegociateursService,
    private readonly toastService: ToastService,
    private readonly modalService: BsModalService,
    private readonly refreshService: RefreshService
  ) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.negociateursService.lister().subscribe((res: Negociateur[]) => {
      this.negociateurs = res;
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      if (!!subscription) {
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
      (this.modalActive.content as EditerNegociateurModalComponent).enregistrer.subscribe(nego => {
        this.negociateursService.enregistrer(nego).subscribe(() => {
          this.toastService.success('gestion.negociateur.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      }),
      (this.modalActive.content as EditerNegociateurModalComponent).modifier.subscribe(nego => {
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
      this.modalActive = null;
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

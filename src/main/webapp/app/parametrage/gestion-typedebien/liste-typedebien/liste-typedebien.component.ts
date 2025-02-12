import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { TypeDeBiensService } from '../../../core/service/metier/typedebiens.service';
import { RefreshService } from '../../../core/service/refresh.service';
import { ToastService } from '../../../core/service/toast.service';
import { ConfigModalCommun } from '../../../core/configuration/config-modal-commun.class';
import { Mode } from '../../../core/model/metier/mode.enum';
import { EditerTypeDeBienModalComponent } from '../editer-typedebien/editer-typedebien-modal.component';
import { TypeDeBien } from '../../../core/model/metier/typedebien.model';
import { Negociateur } from 'app/core/model/metier/negociateur.model';

@Component({
  selector: 'jhi-typedebiens',
  templateUrl: './liste-typedebien.component.html'
})
export class ListeTypeDeBienComponent implements OnInit, OnDestroy {
  public typeDeBiens: TypeDeBien[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  private modalActive: BsModalRef | undefined;

  constructor(
    private typeDeBiensService: TypeDeBiensService,
    private toastService: ToastService,
    private modalService: BsModalService,
    private refreshService: RefreshService
  ) {}

  ngOnInit(): void {
    this.subscriptions.push(
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

  // Ouverture de la modal des négociateurs
  public ouvrirModalTypeDeBien(typeDeBien: TypeDeBien, mode: Mode): void {
    this.modalActive = this.modalService.show(
      EditerTypeDeBienModalComponent,
      Object.assign(new ConfigModalCommun(), {
        class: 'modal-dialog-centered modal-lg',
        initialState: {
          mode,
          typeDeBien
        }
      })
    );
    this.subscriptions.push(
      (this.modalActive.content as EditerTypeDeBienModalComponent).fermer.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerTypeDeBienModalComponent).annuler.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerTypeDeBienModalComponent).enregistrer.subscribe((nego: Negociateur) => {
        this.typeDeBiensService.enregistrer(nego).subscribe(() => {
          this.toastService.success('gestion.typedebien.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      }),
      (this.modalActive.content as EditerTypeDeBienModalComponent).modifier.subscribe((nego: Negociateur) => {
        this.typeDeBiensService.modifier(nego).subscribe(() => {
          this.toastService.success('gestion.typedebien.action.success');
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

  public creerTypeDeBien(): void {
    const typeDeBien = new TypeDeBien();
    this.ouvrirModalTypeDeBien(typeDeBien, Mode.CREATION);
  }

  public modifierTypeDeBien(typeDeBien: TypeDeBien): void {
    this.ouvrirModalTypeDeBien(typeDeBien, Mode.EDITION);
  }
}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { Exercice } from 'app/core/model/metier/exercice.model';
import { Subscription } from 'rxjs';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { ExercicesService } from 'app/core/service/metier/exercices.service';
import { RefreshService } from 'app/core/service/refresh.service';
import { ToastService } from 'app/core/service/toast.service';
import { ConfigModalCommun } from 'app/core/configuration/config-modal-commun.class';
import { Mode } from 'app/core/model/metier/mode.enum';
import { EditerExerciceModalComponent } from '../editer-exercice/editer-exercice-modal.component';
import { Negociateur } from 'app/core/model/metier/negociateur.model';

@Component({
  selector: 'jhi-exercices',
  templateUrl: './liste-exercices.component.html'
})
export class ListeExercicesComponent implements OnInit, OnDestroy {
  public exercices: Exercice[] = [];
  // Liste subscription
  private readonly subscriptions: Subscription[] = [];
  private modalActive: BsModalRef | undefined;

  constructor(
    private exercicesService: ExercicesService,
    private toastService: ToastService,
    private modalService: BsModalService,
    private refreshService: RefreshService
  ) {}

  ngOnInit(): void {
    this.subscriptions.push(
      this.exercicesService.lister().subscribe((res: Exercice[]) => {
        this.exercices = res;
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
  public ouvrirModalExercice(exercice: Exercice, mode: Mode): void {
    this.modalActive = this.modalService.show(
      EditerExerciceModalComponent,
      Object.assign(new ConfigModalCommun(), {
        class: 'modal-dialog-centered modal-lg',
        initialState: {
          mode,
          exercice
        }
      })
    );
    this.subscriptions.push(
      (this.modalActive.content as EditerExerciceModalComponent).fermer.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerExerciceModalComponent).annuler.subscribe(() => {
        this.fermerModal();
      }),
      (this.modalActive.content as EditerExerciceModalComponent).enregistrer.subscribe((nego: Negociateur) => {
        this.exercicesService.enregistrer(nego).subscribe(() => {
          this.toastService.success('gestion.exercice.action.success');
          this.fermerModal();
          this.refreshService.refresh();
        });
      }),
      (this.modalActive.content as EditerExerciceModalComponent).modifier.subscribe((nego: Negociateur) => {
        this.exercicesService.modifier(nego).subscribe(() => {
          this.toastService.success('gestion.exercice.action.success');
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

  public creerExercice(): void {
    const exercice = new Exercice();
    this.ouvrirModalExercice(exercice, Mode.CREATION);
  }

  public modifierExercice(exercice: Exercice): void {
    this.ouvrirModalExercice(exercice, Mode.EDITION);
  }
}

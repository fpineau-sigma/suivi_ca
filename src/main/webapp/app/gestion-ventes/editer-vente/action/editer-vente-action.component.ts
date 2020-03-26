import {Location} from '@angular/common';
import {Component, Input} from '@angular/core';
import {ControlContainer, NgForm} from '@angular/forms';
import {Vente} from '../../../core/model/metier/vente.model';
import {Mode} from '../../../core/model/metier/mode.enum';
import {VentesService} from '../../../core/service/metier/ventes.service';
import {ToastService} from '../../../core/service/toast.service';
import {faCheck} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'jhi-editer-vente-action',
  templateUrl: './editer-vente-action.component.html',
  viewProviders: [{provide: ControlContainer, useExisting: NgForm}]
})
export class EditerVenteActionComponent {

  public faCheck = faCheck;

  @Input() public mode: Mode;
  @Input() public vente: Vente;

  constructor(private location: Location,
              public form: NgForm,
              private ventesService: VentesService,
              private toastService: ToastService) {
  }

  get Mode(): typeof Mode {
    return Mode;
  }

  public retour(): void {
    this.location.back();
  }

  public modifierVente(): void {
    this.ventesService.modifier(this.vente).subscribe(() => {
      this.toastService.success('gestion.negociateur.action.success');
    });
  }

  public enregistrerVente(): void {
    this.ventesService.enregistrer(this.vente).subscribe(() => {
      this.toastService.success('gestion.negociateur.action.success');
    });
  }
}

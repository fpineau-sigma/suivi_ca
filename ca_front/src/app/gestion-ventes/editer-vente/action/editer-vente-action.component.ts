import {Location} from '@angular/common';
import {Component, Input} from '@angular/core';
import {ControlContainer, NgForm} from '@angular/forms';
import {Vente} from '../../../core/model/vente.model';
import {Mode} from '../../../core/model/mode.enum';
import {VentesService} from '../../../core/service/ventes.service';
import {ToastService} from '../../../core/service/toast.service';


@Component({
  selector: 'app-editer-vente-action',
  templateUrl: './editer-vente-action.component.html',
  viewProviders: [{provide: ControlContainer, useExisting: NgForm}]
})
export class EditerVenteActionComponent {

  @Input() public mode: Mode;
  @Input() public vente: Vente;

  constructor(private readonly location: Location,
              public readonly form: NgForm,
              private readonly ventesService: VentesService,
              private readonly toastService: ToastService) {
  }

  get Mode() {
    return Mode;
  }

  public retour() {
    this.location.back();
  }

  public modifierVente() {
    this.ventesService.modifier(this.vente).subscribe(() => {
      this.toastService.success('gestion.negociateur.action.success');
    });
  }

  public enregistrerVente() {
    this.ventesService.enregistrer(this.vente).subscribe(() => {
      this.toastService.success('gestion.negociateur.action.success');
    });
  }
}

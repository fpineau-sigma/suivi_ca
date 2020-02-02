import {Component, Input} from '@angular/core';
import {ControlContainer, NgForm} from '@angular/forms';
import {Mode} from "../../../core/model/mode.enum";
import {Vente} from "../../../core/model/vente.model";
import {Origines} from "../../../core/model/origine.enum";


@Component({
  selector: 'app-editer-vente-honoraires',
  templateUrl: './editer-vente-honoraires.component.html',
  viewProviders: [{provide: ControlContainer, useExisting: NgForm}]
})
export class EditerVenteHonorairesComponent {

  @Input() public mode: Mode;
  @Input() public vente: Vente;


  constructor(public readonly form: NgForm) {
  }

  get Mode() {
    return Mode;
  }

  get origines() {
    return Origines;
  }
}

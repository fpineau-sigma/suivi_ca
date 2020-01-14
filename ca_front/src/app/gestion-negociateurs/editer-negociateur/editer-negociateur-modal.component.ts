import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Negociateur} from "../../core/model/negociateur.model";
import {Mode} from "../../core/model/mode.enum";

@Component({
  selector: 'app-negociateur',
  templateUrl: './editer-negociateur-modal.component.html'
})
export class EditerNegociateurModalComponent{

  @Output() public enregistrer: EventEmitter<Negociateur> = new EventEmitter();
  @Output() public modifier: EventEmitter<Negociateur> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<Negociateur> = new EventEmitter();
  @Input() public negociateur : Negociateur;
  @Input() public mode: Mode;

  constructor(){}

  get ModeEnum() {
    return Mode;
  }

}

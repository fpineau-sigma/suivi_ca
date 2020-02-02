import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Negociateur} from '../../core/model/negociateur.model';
import {Mode} from '../../core/model/mode.enum';


@Component({
  selector: 'app-negociateur',
  templateUrl: './editer-negociateur-modal.component.html'
})
export class EditerNegociateurModalComponent implements OnInit {

  @Output() public enregistrer: EventEmitter<Negociateur> = new EventEmitter();
  @Output() public modifier: EventEmitter<Negociateur> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<Negociateur> = new EventEmitter();
  @Input() public negociateur: Negociateur;
  @Input() public mode: Mode;

  public negociateurEditer: Negociateur = {};

  constructor() {
  }

  public ngOnInit(): void {
    Object.assign(this.negociateurEditer, this.negociateur);
  }

  get ModeEnum() {
    return Mode;
  }
}

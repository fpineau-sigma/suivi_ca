import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Origine} from '../../../core/model/metier/origine.model';
import {Mode} from '../../../core/model/metier/mode.enum';
import {faCheck} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'jhi-origine',
  templateUrl: './editer-origine-modal.component.html'
})
export class EditerOrigineModalComponent implements OnInit {

  @Output() public enregistrer: EventEmitter<Origine> = new EventEmitter();
  @Output() public modifier: EventEmitter<Origine> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<Origine> = new EventEmitter();
  @Input() public origine!: Origine;
  @Input() public mode!: Mode;

  public faCheck = faCheck;

  public origineEditer: Origine = {};

  constructor() {
  }

  public ngOnInit(): void {
    Object.assign(this.origineEditer, this.origine);
  }

  get ModeEnum(): typeof Mode {
    return Mode;
  }
}

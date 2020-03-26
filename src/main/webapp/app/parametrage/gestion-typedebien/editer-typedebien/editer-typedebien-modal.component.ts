import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Mode} from '../../../core/model/metier/mode.enum';
import {TypeDeBien} from '../../../core/model/metier/typedebien.model';
import {faCheck} from '@fortawesome/free-solid-svg-icons/faCheck';


@Component({
  selector: 'jhi-typedebien',
  templateUrl: './editer-typedebien-modal.component.html'
})
export class EditerTypeDeBienModalComponent implements OnInit {

  @Output() public enregistrer: EventEmitter<TypeDeBien> = new EventEmitter();
  @Output() public modifier: EventEmitter<TypeDeBien> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<TypeDeBien> = new EventEmitter();
  @Input() public typeDeBien!: TypeDeBien;
  @Input() public mode!: Mode;

  public typeDeBienEditer: TypeDeBien = {};
  public faCheck = faCheck;

  constructor() {
  }

  public ngOnInit(): void {
    Object.assign(this.typeDeBienEditer, this.typeDeBien);
  }

  get ModeEnum(): typeof Mode {
    return Mode;
  }
}

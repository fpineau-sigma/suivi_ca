import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Mode} from '../../../core/model/mode.enum';
import {TypeDeBien} from '../../../core/model/typedebien.model';


@Component({
  selector: 'app-typedebien',
  templateUrl: './editer-typedebien-modal.component.html'
})
export class EditerTypeDeBienModalComponent implements OnInit {

  @Output() public enregistrer: EventEmitter<TypeDeBien> = new EventEmitter();
  @Output() public modifier: EventEmitter<TypeDeBien> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<TypeDeBien> = new EventEmitter();
  @Input() public typeDeBien: TypeDeBien;
  @Input() public mode: Mode;

  public typeDeBienEditer: TypeDeBien = {};

  constructor() {
  }

  public ngOnInit(): void {
    Object.assign(this.typeDeBienEditer, this.typeDeBien);
  }

  get ModeEnum() {
    return Mode;
  }
}

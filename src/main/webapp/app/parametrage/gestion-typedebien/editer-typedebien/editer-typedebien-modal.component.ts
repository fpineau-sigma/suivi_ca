import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Mode } from '../../../core/model/metier/mode.enum';
import { TypeDeBien } from '../../../core/model/metier/typedebien.model';
import { faCheck } from '@fortawesome/free-solid-svg-icons/faCheck';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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

  public faCheck = faCheck;
  public registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  public ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      libelle: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f(): any {
    return this.registerForm.controls;
  }

  public onEnregistrer(): void {
    if (!this.registerForm.invalid) {
      this.enregistrer.emit(this.registerForm.value);
    }
  }

  public onModifier(): void {
    if (!this.registerForm.invalid) {
      this.updateTypeDeBien(this.typeDeBien);
      this.modifier.emit(this.typeDeBien);
    }
  }

  private updateTypeDeBien(typeDeBien: TypeDeBien): void {
    typeDeBien.libelle = this.registerForm.get(['libelle'])!.value;
  }

  get ModeEnum(): typeof Mode {
    return Mode;
  }
}

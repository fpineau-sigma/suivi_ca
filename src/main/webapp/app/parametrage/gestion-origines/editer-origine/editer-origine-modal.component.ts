import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Origine} from '../../../core/model/metier/origine.model';
import {Mode} from '../../../core/model/metier/mode.enum';
import {faCheck} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TypeOrigine} from 'app/core/model/metier/type.origine.enum';

interface EnumItem<E> {
  id: E;
  name: keyof E;
}

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
  public registerForm: FormGroup;


  constructor(private formBuilder: FormBuilder) {
  }

  public ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      libelle: ['', Validators.required],
      typeOrigine: ['']
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
      this.updateOrigine(this.origine);
      this.modifier.emit(this.origine);
    }
  }

  private updateOrigine(origine: Origine): void {
    origine.libelle = this.registerForm.get(['libelle'])!.value;
    origine.typeOrigine = this.registerForm.get(['typeOrigine'])!.value;
  }

  get ModeEnum(): typeof Mode {
    return Mode;
  }

  get TypeOrigines(): typeof TypeOrigine {
    return TypeOrigine;
  }

}

import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Exercice} from 'app/core/model/metier/exercice.model';
import {Mode} from 'app/core/model/metier/mode.enum';
import {faCheck} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {uniqueStringValidator} from 'app/shared/validators/unique.validator';

@Component({
  selector: 'jhi-exercice',
  templateUrl: './editer-exercice-modal.component.html'
})
export class EditerExerciceModalComponent implements OnInit {
  @Output() public enregistrer: EventEmitter<Exercice> = new EventEmitter();
  @Output() public modifier: EventEmitter<Exercice> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<Exercice> = new EventEmitter();
  @Input() public exercice!: Exercice;
  @Input() public exercices!: Exercice[];
  @Input() public mode!: Mode;

  public faCheck = faCheck;
  public registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  public ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      libelle: ['', [Validators.required, uniqueStringValidator(this.exercices)]]
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
      this.updateExercice(this.exercice);
      this.modifier.emit(this.exercice);
    }
  }

  private updateExercice(exercice: Exercice): void {
    exercice.libelle = this.registerForm.get(['libelle'])!.value;
  }

  get ModeEnum(): typeof Mode {
    return Mode;
  }
}

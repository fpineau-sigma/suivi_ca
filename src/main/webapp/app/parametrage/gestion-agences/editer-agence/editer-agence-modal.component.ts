import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Agence} from '../../../core/model/metier/agence.model';
import {Mode} from '../../../core/model/metier/mode.enum';
import {faCheck} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {uniqueStringValidator} from 'app/shared/validators/unique.validator';

@Component({
  selector: 'jhi-agence',
  templateUrl: './editer-agence-modal.component.html'
})
export class EditerAgenceModalComponent implements OnInit {
  @Output() public enregistrer: EventEmitter<Agence> = new EventEmitter();
  @Output() public modifier: EventEmitter<Agence> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<Agence> = new EventEmitter();
  @Input() public agence!: Agence;
  @Input() public agences!: Agence[];
  @Input() public mode!: Mode;

  public faCheck = faCheck;
  public registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  public ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      libelle: ['', [Validators.required, uniqueStringValidator(this.agences)]]
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
      this.updateAgence(this.agence);
      this.modifier.emit(this.agence);
    }
  }

  private updateAgence(agence: Agence): void {
    agence.libelle = this.registerForm.get(['libelle'])!.value;
  }

  get ModeEnum(): typeof Mode {
    return Mode;
  }
}

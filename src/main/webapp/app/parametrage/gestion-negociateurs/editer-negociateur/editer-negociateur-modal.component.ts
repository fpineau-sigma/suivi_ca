import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Negociateur } from 'app/core/model/metier/negociateur.model';
import { Mode } from 'app/core/model/metier/mode.enum';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-negociateur',
  templateUrl: './editer-negociateur-modal.component.html'
})
export class EditerNegociateurModalComponent implements OnInit {
  @Output() public enregistrer: EventEmitter<Negociateur> = new EventEmitter();
  @Output() public modifier: EventEmitter<Negociateur> = new EventEmitter();
  @Output() public annuler: EventEmitter<void> = new EventEmitter();
  @Output() public fermer: EventEmitter<Negociateur> = new EventEmitter();
  @Input() public negociateur!: Negociateur;
  @Input() public mode!: Mode;

  public faCheck = faCheck;
  public registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  public ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      nomCourt: ['', Validators.required],
      nom: [''],
      prenom: [''],
      actif: ['', Validators.required]
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
      this.updateNegociateur(this.negociateur);
      this.modifier.emit(this.negociateur);
    }
  }

  private updateNegociateur(negociateur: Negociateur): void {
    negociateur.nomCourt = this.registerForm.get(['nomCourt'])!.value;
    negociateur.prenom = this.registerForm.get(['prenom'])!.value;
    negociateur.nom = this.registerForm.get(['nom'])!.value;
    negociateur.actif = this.registerForm.get(['actif'])!.value;
  }

  get ModeEnum(): any {
    return Mode;
  }
}

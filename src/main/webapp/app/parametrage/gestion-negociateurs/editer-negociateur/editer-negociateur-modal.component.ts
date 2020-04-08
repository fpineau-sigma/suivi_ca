import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Negociateur} from 'app/core/model/metier/negociateur.model';
import {Mode} from 'app/core/model/metier/mode.enum';
import {faCheck} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Objectif} from 'app/core/model/metier/objectif.model';
import {ClesSessionStorage} from 'app/shared/constants/storage.constants';

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
  public objectifEnCours: Objectif;

  constructor(private formBuilder: FormBuilder) {
  }

  public ngOnInit(): void {
    this.objectifEnCours = this.negociateur.objectifs.find(value => value.exerciceId === +sessionStorage.getItem(ClesSessionStorage.EXERCICE));
    if (this.objectifEnCours == null) {
      this.objectifEnCours = new Objectif();
    }
    this.registerForm = this.formBuilder.group({
      nomCourt: ['', Validators.required],
      nom: [''],
      prenom: [''],
      actif: ['', Validators.required],
      objectif: ['', [Validators.required, Validators.pattern('[0-9]+(\\.[0-9][0-9]?)?')]]
    });
  }

  // convenience getter for easy access to form fields
  get f(): any {
    return this.registerForm.controls;
  }

  public onEnregistrer(): void {
    if (!this.registerForm.invalid) {
      this.updateNegociateur(this.negociateur);
      this.enregistrer.emit(this.negociateur);
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
    this.objectifEnCours.montant = this.registerForm.get(['objectif'])!.value;
    if (this.objectifEnCours.id == null) {
      negociateur.objectifs.push(this.objectifEnCours);
    }
  }

  get ModeEnum(): any {
    return Mode;
  }
}

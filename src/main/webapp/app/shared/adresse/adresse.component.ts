import { Component, Input, OnInit } from '@angular/core';
import { Mode } from '../../core/model/metier/mode.enum';
import { Adresse } from 'app/core/model/metier/adresse.model';
import { ControlContainer, FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-adresse',
  templateUrl: './adresse.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class AdresseComponent implements OnInit {
  @Input() public adresse: Adresse;
  @Input() public mode: Mode;

  public venteAdresseForm: FormGroup;

  constructor(private venteForm: FormGroupDirective, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.venteAdresseForm = this.venteForm.form;
    this.venteAdresseForm.addControl(
      '_adresse',
      this.formBuilder.group({
        adresseNumero: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        adresseNomVoie: ['', Validators.required],
        adresseCodePostal: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        adresseVille: ['', Validators.required]
      })
    );
  }

  // convenience getter for easy access to form fields
  get f(): any {
    return this.venteAdresseForm.controls;
  }
}

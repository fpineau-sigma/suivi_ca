import {Location} from '@angular/common';
import {Component, Input} from '@angular/core';
import {ControlContainer, FormControlDirective, FormGroupDirective} from '@angular/forms';
import {Vente} from 'app/core/model/metier/vente.model';
import {Mode} from 'app/core/model/metier/mode.enum';
import {VentesService} from 'app/core/service/metier/ventes.service';
import {ToastService} from 'app/core/service/toast.service';
import {faCheck} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'jhi-editer-vente-action',
  templateUrl: './editer-vente-action.component.html',
  viewProviders: [{provide: ControlContainer, useExisting: FormControlDirective}]
})
export class EditerVenteActionComponent {

  public faCheck = faCheck;

  @Input() public mode: Mode;
  @Input() public vente: Vente;

  constructor(private location: Location,
              public venteForm: FormGroupDirective,
              private ventesService: VentesService,
              private toastService: ToastService) {
  }

  get Mode(): typeof Mode {
    return Mode;
  }

  public retour(): void {
    this.location.back();
  }

  public modifierVente(): void {
    this.mettreAjourVente();
    this.ventesService.modifier(this.vente).subscribe(() => {
      this.toastService.success('gestion.negociateur.action.success');
    });
  }

  public enregistrerVente(): void {
    this.mettreAjourVente();
    this.ventesService.enregistrer(this.vente).subscribe(() => {
      this.toastService.success('gestion.negociateur.action.success');
    });
  }

  public mettreAjourVente(): void {
    // Mise à jour partie honoraires
    this.vente.dateCompromis = this.venteForm.form.get('_honoraires').get('venteDateVenteCompromis').value;
    this.vente.origine = this.venteForm.form.get('_honoraires').get('venteOrigine').value;
    this.vente.honorairesTTC = this.venteForm.form.get('_honoraires').get('venteHonorairesTTC').value;
    this.vente.honorairesHT = this.venteForm.form.get('_honoraires').get('venteHonorairesHT').value;
    this.vente.dateActeAuthentique = this.venteForm.form.get('_honoraires').get('venteDateActeAuthentique').value;
    this.vente.numeroFacture = this.venteForm.form.get('_honoraires').get('venteNumeroFacture').value;
    this.vente.typeDeBien = this.venteForm.form.get('_honoraires').get('venteTypeBien').value;
    // Mise à jour partie adresse
    this.vente.adresse.numeroVoie = this.venteForm.form.get('_adresse').get('adresseNumero').value;
    this.vente.adresse.ville = this.venteForm.form.get('_adresse').get('adresseVille').value;
    this.vente.adresse.codePostal = this.venteForm.form.get('_adresse').get('adresseCodePostal').value;
    this.vente.adresse.nomVoie = this.venteForm.form.get('_adresse').get('adresseNomVoie').value;
  }
}

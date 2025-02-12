import { Component, Input, OnInit } from '@angular/core';
import { ControlContainer, FormBuilder, FormControlDirective, FormGroup, Validators } from '@angular/forms';
import { Mode } from '../../../core/model/metier/mode.enum';
import { Vente } from '../../../core/model/metier/vente.model';
import { Personne } from '../../../core/model/metier/personne.model';
import { faTrash } from '@fortawesome/free-solid-svg-icons/faTrash';
import { faEdit } from '@fortawesome/free-solid-svg-icons/faEdit';
import { ColumnMode } from '@swimlane/ngx-datatable';

export enum ChampVendeurAcquereur {
  ACQUEREUR,
  VENDEUR
}

@Component({
  selector: 'jhi-editer-vente-acquereurs-vendeurs',
  templateUrl: './editer-vente-acquereurs-vendeurs.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormControlDirective }]
})
export class EditerVenteAcquereursVendeursComponent implements OnInit {
  @Input() public mode: Mode;
  @Input() public vente: Vente;
  @Input() public champ: ChampVendeurAcquereur;
  public personnes: Personne[] = [];

  public faTrash = faTrash;
  public faEdit = faEdit;
  public personneEnEdition: Personne;
  private personneSelectionnee: Personne;
  public editionEnCours = false;

  public venteAcquereursForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.venteAcquereursForm = this.formBuilder.group({
      personneNom: ['', Validators.required],
      personnePrenom: ['', Validators.required]
    });
    if (this.champ === ChampVendeurAcquereur.ACQUEREUR) {
      this.personnes = this.vente.acquereurs;
    } else {
      this.personnes = this.vente.vendeurs;
    }
  }

  get Mode(): typeof Mode {
    return Mode;
  }

  /**
   * Expose l'enum ColumnMode pour le composant ngx-datatable
   */
  public get ColumnMode(): typeof ColumnMode {
    return ColumnMode;
  }

  public supprimerPersonne(personneEditer: Personne): void {
    this.personnes = this.personnes.filter(personne => personne !== personneEditer);
    this.personnes = [...this.personnes];
    this.miseAjourVente();
  }

  public editerPersonne(personne: Personne): void {
    if (null === personne) {
      this.personneEnEdition = Object.assign({}, new Personne());
    } else {
      this.personneSelectionnee = personne;
      this.personneEnEdition = Object.assign({}, personne);
    }
    this.editionEnCours = true;
    this.miseAjourVente();
  }

  public ajouterPersonne(): void {
    this.personneEnEdition.nom = this.venteAcquereursForm.get(['personneNom'])!.value;
    this.personneEnEdition.prenom = this.venteAcquereursForm.get(['personnePrenom'])!.value;

    const index = this.personnes.indexOf(this.personneSelectionnee);
    if (index === -1) {
      this.personnes = [...this.personnes, this.personneEnEdition];
    } else {
      this.personnes[index] = this.personneEnEdition;
      this.personnes = [...this.personnes];
    }
    this.personneEnEdition = null;
    this.personneSelectionnee = null;
    this.editionEnCours = false;
    this.miseAjourVente();
  }

  private miseAjourVente(): void {
    if (this.champ === ChampVendeurAcquereur.ACQUEREUR) {
      this.vente.acquereurs = [...this.personnes];
    } else {
      this.vente.vendeurs = [...this.personnes];
    }
  }
}

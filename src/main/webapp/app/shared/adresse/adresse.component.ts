import {Component, Input, OnInit} from '@angular/core';
import {Mode} from '../../core/model/metier/mode.enum';
import {Adresse} from 'app/core/model/metier/adresse.model';


@Component({
  selector: 'jhi-adresse',
  templateUrl: './adresse.component.html'
})
export class AdresseComponent implements OnInit {

  @Input() public adresse: Adresse;
  @Input() public mode: Mode;

  public adresseEditer: Adresse = new Adresse();

  constructor() {
  }

  ngOnInit(): void {
    if (this.adresse) {
      this.adresseEditer = this.adresse;
    }
  }

}

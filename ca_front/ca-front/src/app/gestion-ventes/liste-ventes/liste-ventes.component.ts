import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-ventes',
  templateUrl: './liste-ventes.component.html'
})
export class ListeVentesComponent implements OnInit{

  ngOnInit(): void {
  console.log("Ventes") ;
  }

}

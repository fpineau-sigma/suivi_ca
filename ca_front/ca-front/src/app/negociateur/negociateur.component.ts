import { Component, OnInit } from '@angular/core';
import { NegociateurService } from './service/negociateur.service';

@Component({
  selector: 'app-negociateur',
  templateUrl: './negociateur.component.html',
  styleUrls: ['./negociateur.component.css']
})

export class NegociateurComponent implements OnInit {

  negociateurs: Array<any>;

  constructor(private negociateurService: NegociateurService) { }

  ngOnInit() {
   this.negociateurService.getAll().subscribe(data => {
          this.negociateurs = data;
        });
    }
}

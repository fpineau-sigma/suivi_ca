import { Component, ViewChild } from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { CommissionService } from './service/commission.service';
import {Observable} from 'rxjs/Observable';
import {merge} from 'rxjs/observable/merge';
import {of as observableOf} from 'rxjs/observable/of';
import {catchError} from 'rxjs/operators/catchError';
import {map} from 'rxjs/operators/map';
import {startWith} from 'rxjs/operators/startWith';
import {switchMap} from 'rxjs/operators/switchMap';

@Component({
  selector: 'app-commission',
  templateUrl: './commission.component.html',
  styleUrls: ['./commission.component.css']
})
export class CommissionComponent  {

  commissions: Array<Commission>;
  displayedColumns = ['adresse', 'nom', 'montant'];
  dataSource: MatTableDataSource<Commission>;

  resultsLength = 0;
  isLoadingResults = false;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private commissionService : CommissionService) {
    let commissions = new Array();
    var liste= this.commissionService.getAll();
    var index = 0;
    liste.forEach(function( element){
      element.forEach(function(element){
        commissions.push(createCommission(index ,element));
        index++;
      });
    });
    this.commissions = commissions;
    this.dataSource = new MatTableDataSource(this.commissions);
  }

  ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}


/** Builds and returns a new User. */
function createCommission(id: number, commission: any): Commission {

  return {
    id: id.toString(),
    adresse: commission[0],
    nom: commission[1],
    montant: commission[2]
  };
}

export interface Commission {
  id: string;
  adresse: string;
  nom: string;
  montant: number;
}

import { Component, ViewChild } from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { NegociateurService } from './service/negociateur.service';
import {Observable} from 'rxjs/Observable';
import {merge} from 'rxjs/observable/merge';
import {of as observableOf} from 'rxjs/observable/of';
import {catchError} from 'rxjs/operators/catchError';
import {map} from 'rxjs/operators/map';
import {startWith} from 'rxjs/operators/startWith';
import {switchMap} from 'rxjs/operators/switchMap';

@Component({
  selector: 'app-negociateur',
  templateUrl: './negociateur.component.html',
  styleUrls: ['./negociateur.component.css']
})

export class NegociateurComponent  {

  negociateurs : Array<any>;
  displayedColumns = ['nomCourt'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private negociateurService: NegociateurService) {
    let negociateurs = new Array();
    var liste= this.negociateurService.getAll();
    liste.forEach(function( element){
      element.forEach(function(element){
        negociateurs.push(element);
      });
    });
    this.negociateurs = negociateurs;
    this.dataSource = new MatTableDataSource(this.negociateurs);
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

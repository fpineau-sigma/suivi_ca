import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { NegociateurService } from './service/negociateur.service';

@Component({
  selector: 'app-negociateur',
  templateUrl: './negociateur.component.html',
  styleUrls: ['./negociateur.component.css']
})

export class NegociateurComponent implements OnInit {

  displayedColumns = ['nomCourt'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private negociateurService: NegociateurService) { }

  ngOnInit() {
    this.negociateurService.getAll().subscribe(p => {
      this.dataSource = new MatTableDataSource(p);
    });
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}

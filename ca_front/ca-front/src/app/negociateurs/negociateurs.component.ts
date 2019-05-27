import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { NegociateursService } from './negociateurs.service';

@Component({
  selector: 'app-negociateurs',
  templateUrl: './negociateurs.component.html',
  styleUrls: ['./negociateurs.component.scss']
})

export class NegociateursComponent implements OnInit {

  displayedColumns = ['nomCourt'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private negociateursService: NegociateursService) { }

  ngOnInit() {
    this.negociateursService.getAll().subscribe(p => {
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

import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { VentesService } from './ventes.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-ventes',
  templateUrl: './ventes.component.html',
  styleUrls: ['./ventes.component.scss']
})
export class VentesComponent implements OnInit {

  displayedColumns = ['dateVente'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private ventesService: VentesService) { }

  ngOnInit() {
    this.ventesService.getAll().subscribe(p => {
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

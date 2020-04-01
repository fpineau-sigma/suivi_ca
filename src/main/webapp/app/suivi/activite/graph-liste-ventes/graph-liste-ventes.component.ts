import { Component, OnInit } from '@angular/core';
import * as d3Shape from 'd3-shape';
import * as d3 from 'd3-selection';
import * as d3Scale from 'd3-scale';
import * as d3Array from 'd3-array';
import * as d3Axis from 'd3-axis';
import { VisualisationVentesService } from 'app/core/service/visualisation/visualisation.ventes.service';
import { VenteParMois } from 'app/core/model/visualisation/venteparmois.model';

@Component({
  selector: 'jhi-graph-list-ventes',
  templateUrl: './graph-liste-ventes.component.html'
})
export class GraphListeVentesComponent implements OnInit {
  title = "Ventes de l'année";

  private margin = { top: 20, right: 20, bottom: 30, left: 50 };
  private width: number;
  private height: number;
  private x: any;
  private y: any;
  private svg: any;
  private line: d3Shape.Line<[number, number]>;
  private ventesParMois: VenteParMois[] = [];

  constructor(private visualisationVentesService: VisualisationVentesService) {}

  ngOnInit(): void {
    this.visualisationVentesService.venteParMois().subscribe((res: VenteParMois[]) => {
      this.ventesParMois = res;
      this.width = 450 - this.margin.left - this.margin.right;
      this.height = 250 - this.margin.top - this.margin.bottom;
      this.initSvg();
      this.initAxis();
      this.drawAxis();
      this.drawLine();
    });
  }

  private initSvg(): void {
    this.svg = d3
      .select('#chart')
      .append('g')
      .attr('transform', 'translate(' + this.margin.left + ',' + this.margin.top + ')');

    this.svg
      .append('text')
      .attr('x', this.width / 2)
      .attr('y', 0 - this.margin.top / 2)
      .attr('text-anchor', 'middle')
      .style('font-size', '14px')
      .style('text-decoration', 'underline')
      .text(this.title);
  }

  private initAxis(): void {
    this.x = d3Scale.scaleLinear().range([0, this.width]);
    this.y = d3Scale.scaleLinear().range([this.height, 0]);
    this.x.domain(d3Array.extent(this.ventesParMois, d => d.mois));
    this.y.domain(d3Array.extent(this.ventesParMois, d => d.nombreVente));
  }

  private drawAxis(): void {
    this.svg
      .append('g')
      .attr('class', 'axis axis--x')
      .attr('transform', 'translate(0,' + this.height + ')')
      .call(d3Axis.axisBottom(this.x))
      .append('text')
      .attr('class', 'axis-title')
      .attr('x', this.width)
      .attr('dx', '.80em')
      .style('text-anchor', 'end')
      .text('Mois');

    this.svg
      .append('g')
      .attr('class', 'axis axis--y')
      .call(d3Axis.axisLeft(this.y))
      .append('text')
      .attr('class', 'axis-title')
      .attr('transform', 'rotate(-90)')
      .attr('y', 6)
      .attr('dy', '.71em')
      .style('text-anchor', 'end')
      .text('Nombre');
  }

  private drawLine(): void {
    this.line = d3Shape
      .line()
      .x((d: any) => this.x(d.mois))
      .y((d: any) => this.y(d.nombreVente));

    this.svg
      .append('path')
      .datum(this.ventesParMois)
      .attr('class', 'line')
      .attr('d', this.line);
  }
}

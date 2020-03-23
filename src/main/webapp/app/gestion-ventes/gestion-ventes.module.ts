import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {VentesService} from '../core/service/application/ventes.service';
import {ListeVentesComponent} from './liste-ventes/liste-ventes.component';
import {EditerVenteModule} from './editer-vente/editer-vente.module';
import {gestionVentesRoutes} from './gestion-ventes.route';
import {CaClemenceSharedModule} from 'app/shared/shared.module';


@NgModule({
  imports: [RouterModule.forChild(gestionVentesRoutes), CaClemenceSharedModule, EditerVenteModule],
  declarations: [ListeVentesComponent],
  providers: [VentesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionVentesModule {
}

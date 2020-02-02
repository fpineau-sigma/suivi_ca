import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {VentesService} from '../core/service/ventes.service';
import {ListeVentesComponent} from './liste-ventes/liste-ventes.component';
import {EditerVenteModule} from './editer-vente/editer-vente.module';
import {gestionVentesRoutes} from './gestion-ventes.route';


@NgModule({
  imports: [RouterModule.forChild(gestionVentesRoutes), SharedModule, EditerVenteModule],
  declarations: [ListeVentesComponent],
  providers: [VentesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionVentesModule {
}

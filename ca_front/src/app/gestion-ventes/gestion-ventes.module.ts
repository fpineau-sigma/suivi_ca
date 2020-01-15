import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ListeVentesComponent} from "./liste-ventes/liste-ventes.component";
import {gestionVentesRoutes} from "./gestion-ventes.route";
import {SharedModule} from "../shared/shared.module";
import {VentesService} from "../core/service/ventes.service";


@NgModule({
  imports: [RouterModule.forChild(gestionVentesRoutes), SharedModule],
  declarations: [ListeVentesComponent],
  providers: [VentesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionVentesModule {
}
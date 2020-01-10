import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ListeVentesComponent} from "./liste-ventes/liste-ventes.component";
import {suiviVentesRoutes} from "./gestion-ventes.route";
import {SharedModule} from "../shared/shared.module";

@NgModule({
  imports: [RouterModule.forChild(suiviVentesRoutes), SharedModule],
  declarations: [ListeVentesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionVentesModule {
}

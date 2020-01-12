import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ListeNegociateursComponent} from "./liste-negociateurs/liste-negociateurs.component";
import {suiviNegociateursRoutes} from "./gestion-negociateurs.route";
import {SharedModule} from "../shared/shared.module";

@NgModule({
  imports: [RouterModule.forChild(suiviNegociateursRoutes),
    SharedModule],
  declarations: [ListeNegociateursComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GestionNegociateursModule {
}

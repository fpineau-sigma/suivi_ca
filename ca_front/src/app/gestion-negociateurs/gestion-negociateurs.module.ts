import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ListeNegociateursComponent} from "./liste-negociateurs/liste-negociateurs.component";
import {gestionNegociateursRoutes} from "./gestion-negociateurs.route";
import {SharedModule} from "../shared/shared.module";
import {EditerNegociateurModalComponent} from "./editer-negociateur/editer-negociateur-modal.component";
import {NegociateursService} from "../core/service/negociateurs.service";

@NgModule({
  imports: [RouterModule.forChild(gestionNegociateursRoutes),
    SharedModule],
  declarations: [ListeNegociateursComponent, EditerNegociateurModalComponent],
  entryComponents: [EditerNegociateurModalComponent],
  providers: [NegociateursService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GestionNegociateursModule {
}
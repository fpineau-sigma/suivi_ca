import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ListeCaNegociateursComponent} from './liste-ca-negociateurs/liste-ca-negociateurs.component';
import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {NegociateursService} from 'app/core/service/metier/negociateurs.service';
import {suiviCaNegociateursRoutes} from 'app/suivi/ca-negociateurs/ca-negociateurs.route';
import {TableauVentesNegociateurComponent} from 'app/suivi/ca-negociateurs/tableau-ventes-negociateur/tableau-ventes-negociateur.component';
import {AccordionModule} from 'ngx-bootstrap';

@NgModule({
  imports: [RouterModule.forChild(suiviCaNegociateursRoutes), CaClemenceSharedModule, AccordionModule],
  declarations: [ListeCaNegociateursComponent, TableauVentesNegociateurComponent],
  providers: [NegociateursService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuiviCaNegociateursModule {
}

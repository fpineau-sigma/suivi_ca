import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CaAgenceComponent} from './ca-agence/ca-agence.component';
import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {suiviCaAgenceRoutes} from 'app/suivi/ca-agence/ca-agence.route';
import {TableauListeObjectifsComponent} from 'app/suivi/ca-agence/tableau-liste-objectifs/tableau-liste-objectifs.component';
import {ObjectifService} from 'app/core/service/metier/objectifs.service';

@NgModule({
  imports: [RouterModule.forChild(suiviCaAgenceRoutes), CaClemenceSharedModule],
  declarations: [CaAgenceComponent, TableauListeObjectifsComponent],
  providers: [ObjectifService],
  exports: [
    TableauListeObjectifsComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuiviCaAgenceModule {
}

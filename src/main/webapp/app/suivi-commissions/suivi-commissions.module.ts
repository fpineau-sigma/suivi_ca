import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ListeCommissionsComponent} from './liste-commissions/liste-commissions.component';
import {CommissionsService} from '../core/service/application/commissions.service';
import {suiviCommissionsRoutes} from './suivi-commissions.route';
import {CaClemenceSharedModule} from 'app/shared/shared.module';


@NgModule({
  imports: [RouterModule.forChild(suiviCommissionsRoutes), CaClemenceSharedModule],
  declarations: [ListeCommissionsComponent],
  providers: [CommissionsService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SuiviCommissionsModule {
}

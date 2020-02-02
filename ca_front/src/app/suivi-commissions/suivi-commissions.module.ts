import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {ListeCommissionsComponent} from './liste-commissions/liste-commissions.component';
import {CommissionsService} from '../core/service/commissions.service';
import {suiviCommissionsRoutes} from './suivi-commissions.route';


@NgModule({
  imports: [RouterModule.forChild(suiviCommissionsRoutes), SharedModule],
  declarations: [ListeCommissionsComponent],
  providers: [CommissionsService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SuiviCommissionsModule {
}

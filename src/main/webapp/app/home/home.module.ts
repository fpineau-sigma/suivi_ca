import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {HOME_ROUTE} from './home.route';
import {HomeComponent} from './home.component';
import {SuiviActiviteModule} from 'app/suivi/activite/suivi-activite.module';
import {SuiviCaAgenceModule} from 'app/suivi/ca-agence/ca-agence.module';

@NgModule({
  imports: [CaClemenceSharedModule, RouterModule.forChild([HOME_ROUTE]), SuiviActiviteModule, SuiviCaAgenceModule],
  declarations: [HomeComponent]
})
export class CaClemenceHomeModule {
}

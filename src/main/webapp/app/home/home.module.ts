import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaClemenceSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { SuiviActiviteModule } from 'app/suivi/activite/suivi-activite.module';

@NgModule({
  imports: [CaClemenceSharedModule, RouterModule.forChild([HOME_ROUTE]), SuiviActiviteModule],
  declarations: [HomeComponent]
})
export class CaClemenceHomeModule {}

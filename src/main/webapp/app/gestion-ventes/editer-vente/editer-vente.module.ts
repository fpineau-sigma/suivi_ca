import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { EditerVenteAcquereursVendeursComponent } from './acquereurs-vendeurs/editer-vente-acquereurs-vendeurs.component';
import { EditerVenteComponent } from './editer-vente.component';
import { EditerVenteActionComponent } from './action/editer-vente-action.component';
import { EditerVenteCommissionsComponent } from './commissions/editer-vente-commissions.component';
import { EditerVenteHonorairesComponent } from './honoraires/editer-vente-honoraires.component';
import { CaClemenceSharedModule } from 'app/shared/shared.module';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { getDatepickerConfig } from 'app/app.module';

@NgModule({
  declarations: [
    EditerVenteComponent,
    EditerVenteHonorairesComponent,
    EditerVenteActionComponent,
    EditerVenteAcquereursVendeursComponent,
    EditerVenteCommissionsComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [],
  imports: [CommonModule, RouterModule, CaClemenceSharedModule],
  exports: [
    EditerVenteComponent,
    EditerVenteActionComponent,
    EditerVenteHonorairesComponent,
    EditerVenteAcquereursVendeursComponent,
    EditerVenteCommissionsComponent
  ],
  providers: [{ provide: BsDatepickerConfig, useFactory: getDatepickerConfig }]
})
export class EditerVenteModule {}

import {CommonModule} from '@angular/common';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {EditerVenteComponent} from "./editer-vente.component";
import {EditerVenteActionComponent} from "./action/editer-vente-action.component";
import {SharedCommonModule} from "../../shared/shared-common.module";
import {SharedModule} from "../../shared/shared.module";
import {EditerVenteHonorairesComponent} from "./honoraires/editer-vente-honoraires.component";
import {EditerVenteAcquereursVendeursComponent} from "./acquereurs-vendeurs/editer-vente-acquereurs-vendeurs.component";
import {EditerVenteCommissionsComponent} from "./commissions/editer-vente-commissions.component";


@NgModule({
  declarations: [
    EditerVenteComponent,
    EditerVenteHonorairesComponent,
    EditerVenteActionComponent,
    EditerVenteAcquereursVendeursComponent,
    EditerVenteCommissionsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [],
  imports: [
    CommonModule,
    SharedCommonModule,
    RouterModule, SharedModule
  ],
  exports: [
    EditerVenteComponent,
    EditerVenteActionComponent,
    EditerVenteHonorairesComponent,
    EditerVenteAcquereursVendeursComponent,
    EditerVenteCommissionsComponent
  ]
})

export class EditerVenteModule {
}

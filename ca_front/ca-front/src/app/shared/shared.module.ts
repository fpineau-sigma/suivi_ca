import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedCommonModule} from "./shared-common.module";
import {ModalModule} from "ngx-bootstrap";
import {VentesService} from "../core/services/ventes.service";
import {NegociateursService} from "../core/services/negociateurs.service";


@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedCommonModule,
    ModalModule
  ],
  exports: [
    SharedCommonModule,
  ],
  declarations: [
  ],
  providers: [
    VentesService,
    NegociateursService
  ]
})

export class SharedModule {
  static forRoot() {
    return {
      ngModule: SharedModule
    };
  }
}

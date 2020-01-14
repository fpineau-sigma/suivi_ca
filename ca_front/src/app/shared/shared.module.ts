import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedCommonModule} from "./shared-common.module";
import {BsModalService, ModalModule} from "ngx-bootstrap";
import {VentesService} from "../core/service/ventes.service";
import {NegociateursService} from "../core/service/negociateurs.service";
import {ToastComponent} from "./toast/toast.component";
import {RefreshService} from "../core/service/refresh.service";


@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedCommonModule,
    ModalModule.forRoot()
  ],
  exports: [
    SharedCommonModule,
  ],
  declarations: [
  ],
  providers: [
    VentesService,
    NegociateursService,
    RefreshService,
    BsModalService
  ]
})

export class SharedModule {
  static forRoot() {
    return {
      ngModule: SharedModule
    };
  }
}

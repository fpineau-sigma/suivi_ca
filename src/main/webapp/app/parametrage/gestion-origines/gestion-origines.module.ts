import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {OriginesService} from '../../core/service/application/origines.service';
import {EditerOrigineModalComponent} from './editer-origine/editer-origine-modal.component';
import {ListeOriginesComponent} from './liste-origines/liste-origines.component';
import {gestionOriginesRoute} from './gestion-origines.route';
import {CaClemenceSharedModule} from 'app/shared/shared.module';


@NgModule({
  imports: [RouterModule.forChild(gestionOriginesRoute),
    CaClemenceSharedModule],
  declarations: [ListeOriginesComponent, EditerOrigineModalComponent],
  entryComponents: [EditerOrigineModalComponent],
  providers: [OriginesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionOriginesModule {
}

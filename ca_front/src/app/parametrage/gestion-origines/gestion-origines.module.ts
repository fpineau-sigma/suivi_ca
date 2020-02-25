import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {OriginesService} from '../../core/service/origines.service';
import {EditerOrigineModalComponent} from './editer-origine/editer-origine-modal.component';
import {ListeOriginesComponent} from './liste-origines/liste-origines.component';
import {SharedModule} from '../../shared/shared.module';
import {gestionOriginesRoute} from './gestion-origines.route';


@NgModule({
  imports: [RouterModule.forChild(gestionOriginesRoute),
    SharedModule],
  declarations: [ListeOriginesComponent, EditerOrigineModalComponent],
  entryComponents: [EditerOrigineModalComponent],
  providers: [OriginesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionOriginesModule {
}

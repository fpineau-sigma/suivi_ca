import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {gestionNegociateursRoutes} from './gestion-negociateurs.route';
import {EditerNegociateurModalComponent} from './editer-negociateur/editer-negociateur-modal.component';
import {ListeNegociateursComponent} from './liste-negociateurs/liste-negociateurs.component';
import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {NegociateursService} from 'app/core/service/application/negociateurs.service';


@NgModule({
  imports: [RouterModule.forChild(gestionNegociateursRoutes),
    CaClemenceSharedModule],
  declarations: [ListeNegociateursComponent, EditerNegociateurModalComponent],
  entryComponents: [EditerNegociateurModalComponent],
  providers: [NegociateursService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GestionNegociateursModule {
}

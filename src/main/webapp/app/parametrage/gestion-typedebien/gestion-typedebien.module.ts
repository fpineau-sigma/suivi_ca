import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {EditerTypeDeBienModalComponent} from './editer-typedebien/editer-typedebien-modal.component';
import {ListeTypeDeBienComponent} from './liste-typedebien/liste-typedebien.component';
import {gestionTypeDeBienRoute} from './gestion-typedebien.route';
import {TypeDeBiensService} from 'app/core/service/metier/typedebiens.service';
import {CaClemenceSharedModule} from 'app/shared/shared.module';


@NgModule({
  imports: [RouterModule.forChild(gestionTypeDeBienRoute),
    CaClemenceSharedModule],
  declarations: [ListeTypeDeBienComponent, EditerTypeDeBienModalComponent],
  entryComponents: [EditerTypeDeBienModalComponent],
  providers: [TypeDeBiensService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionTypeDeBiensModule {
}

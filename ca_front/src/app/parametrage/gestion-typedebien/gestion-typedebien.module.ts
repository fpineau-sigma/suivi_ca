import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedModule} from '../../shared/shared.module';
import {EditerTypeDeBienModalComponent} from './editer-typedebien/editer-typedebien-modal.component';
import {ListeTypeDeBienComponent} from './liste-typedebien/liste-typedebien.component';
import {gestionTypeDeBienRoute} from './gestion-typedebien.route';
import {TypeDeBiensService} from '../../core/service/typedebiens.service';


@NgModule({
  imports: [RouterModule.forChild(gestionTypeDeBienRoute),
    SharedModule],
  declarations: [ListeTypeDeBienComponent, EditerTypeDeBienModalComponent],
  entryComponents: [EditerTypeDeBienModalComponent],
  providers: [TypeDeBiensService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GestionTypeDeBiensModule {
}

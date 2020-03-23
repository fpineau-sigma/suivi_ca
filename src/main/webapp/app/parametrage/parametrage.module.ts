import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {parametrageRoutes} from './parametrage.route';
import {GestionTypeDeBiensModule} from './gestion-typedebien/gestion-typedebien.module';
import {GestionOriginesModule} from './gestion-origines/gestion-origines.module';
import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {GestionNegociateursModule} from 'app/parametrage/gestion-negociateurs/gestion-negociateurs.module';
import {ImporterModule} from 'app/parametrage/importer/importer.module';


@NgModule({
  imports: [RouterModule.forChild(parametrageRoutes), CaClemenceSharedModule, GestionTypeDeBiensModule, GestionOriginesModule, GestionNegociateursModule, ImporterModule],
  declarations: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ParametrageModule {
}

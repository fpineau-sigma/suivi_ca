import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {parametrageRoutes} from './parametrage.route';
import {ImporterFichierComponent} from './importer/fichier/importer-fichier.component';
import {GestionTypeDeBiensModule} from './gestion-typedebien/gestion-typedebien.module';
import {GestionOriginesModule} from './gestion-origines/gestion-origines.module';


@NgModule({
  imports: [RouterModule.forChild(parametrageRoutes), SharedModule, GestionTypeDeBiensModule, GestionOriginesModule],
  declarations: [ImporterFichierComponent],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ParametrageModule {
}

import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {parametrageRoutes} from './parametrage.route';
import {ImporterFichierComponent} from './importer/fichier/importer-fichier.component';


@NgModule({
  imports: [RouterModule.forChild(parametrageRoutes), SharedModule],
  declarations: [ImporterFichierComponent],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ParametrageModule {
}

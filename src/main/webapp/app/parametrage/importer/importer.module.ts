import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {importerRoute} from './importer.route';
import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {ImporterFichierComponent} from 'app/parametrage/importer/fichier/importer-fichier.component';
import {FichierService} from 'app/core/service/metier/fichier.service';


@NgModule({
  imports: [RouterModule.forChild(importerRoute),
    CaClemenceSharedModule],
  declarations: [ImporterFichierComponent],
  entryComponents: [ImporterFichierComponent],
  providers: [FichierService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ImporterModule {
}

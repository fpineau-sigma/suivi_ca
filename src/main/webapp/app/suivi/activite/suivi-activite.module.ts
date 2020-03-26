import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {GraphListeVentesComponent} from 'app/suivi/activite/graph-liste-ventes/graph-liste-ventes.component';
import {VisualisationVentesService} from 'app/core/service/visualisation/visualisation.ventes.service';


@NgModule({
  imports: [],
  declarations: [GraphListeVentesComponent],
  providers: [VisualisationVentesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [
    GraphListeVentesComponent
  ]
})
export class SuiviActiviteModule {
}

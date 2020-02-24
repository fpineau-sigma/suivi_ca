import {Route} from '@angular/router';
import {ImporterFichierComponent} from './importer-fichier.component';


export const importerFichierRoute: Route = {
  path: 'importFichier',
  component: ImporterFichierComponent,
  data: {
    pageTitle: 'parametrage.importer.fichier.titre',
  },
  canActivate: []
};

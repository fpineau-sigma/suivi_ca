import {ImporterFichierComponent} from './importer-fichier.component';
import {Route} from '@angular/router';


export const importerFichierRoute: Route = {
  'path': '',
  'component': ImporterFichierComponent,
  'data': {
    'pageTitle': 'parametrage.importer.fichier.titre',
  },
  'canActivate': []
};

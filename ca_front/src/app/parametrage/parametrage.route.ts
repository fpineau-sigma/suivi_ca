import {Routes} from '@angular/router';
import {importerFichierRoute} from './importer/fichier/importer-fichier.route';


const PARAMETRAGE_ROUTES = [importerFichierRoute];

export const parametrageRoutes: Routes = [
  {
    path: 'parametrage',
    children: PARAMETRAGE_ROUTES,
    data: {
      pageTitle: 'parametrage.titre',
    },
    canActivate: []
  }
];

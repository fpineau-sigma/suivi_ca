import {Routes} from '@angular/router';
import {importerFichierRoute} from './importer/fichier/importer-fichier.route';
import {gestionOriginesRoute} from './gestion-origines/gestion-origines.route';
import {gestionTypeDeBienRoute} from './gestion-typedebien/gestion-typedebien.route';


const PARAMETRAGE_ROUTES = [importerFichierRoute, ...gestionOriginesRoute, ...gestionTypeDeBienRoute];

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

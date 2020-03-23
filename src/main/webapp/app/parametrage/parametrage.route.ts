import {Routes} from '@angular/router';
import {gestionOriginesRoute} from './gestion-origines/gestion-origines.route';
import {gestionTypeDeBienRoute} from './gestion-typedebien/gestion-typedebien.route';
import {gestionNegociateursRoutes} from 'app/parametrage/gestion-negociateurs';
import {importerRoute} from 'app/parametrage/importer/importer.route';


const PARAMETRAGE_ROUTES = [...gestionOriginesRoute, ...gestionTypeDeBienRoute, ...gestionNegociateursRoutes, ...importerRoute];

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

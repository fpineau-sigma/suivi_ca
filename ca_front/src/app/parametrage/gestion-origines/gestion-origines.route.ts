import {Routes} from '@angular/router';
import {listeOriginesRoute} from './liste-origines/liste-origines.route';

const GESTION_ORIGINES_ROUTES = [listeOriginesRoute];

export const gestionOriginesRoute: Routes = [
  {
    path: 'origines',
    children: GESTION_ORIGINES_ROUTES,
    data: {
      pageTitle: 'gestion.negociateurs.liste'
    },
    canActivate: []
  }
];

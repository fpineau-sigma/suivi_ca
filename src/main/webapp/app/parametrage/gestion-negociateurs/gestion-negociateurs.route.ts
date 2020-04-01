import { Routes } from '@angular/router';
import { listeNegociateursRoute } from './liste-negociateurs/liste-negociateurs.route';

const GESTION_NEGOCIATEURS_ROUTES = [listeNegociateursRoute];

export const gestionNegociateursRoutes: Routes = [
  {
    path: 'parametrage/negociateurs',
    children: GESTION_NEGOCIATEURS_ROUTES,
    data: {
      pageTitle: 'gestion.negociateurs.liste'
    },
    canActivate: []
  }
];

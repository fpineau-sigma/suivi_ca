import { Routes } from '@angular/router';
import { listeAgencesRoute } from './liste-agences/liste-agences.route';

const GESTION_AGENCES_ROUTES = [listeAgencesRoute];

export const gestionAgencesRoute: Routes = [
  {
    path: 'agences',
    children: GESTION_AGENCES_ROUTES,
    data: {
      pageTitle: 'gestion.agences.liste'
    },
    canActivate: []
  }
];

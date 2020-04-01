import { Routes } from '@angular/router';
import { listeExercicesRoute } from './liste-exercices/liste-exercices.route';

const GESTION_EXERCICES_ROUTES = [listeExercicesRoute];

export const gestionExercicesRoute: Routes = [
  {
    path: 'exercices',
    children: GESTION_EXERCICES_ROUTES,
    data: {
      pageTitle: 'gestion.exercices.liste'
    },
    canActivate: []
  }
];

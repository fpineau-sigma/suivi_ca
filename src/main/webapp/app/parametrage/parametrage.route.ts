import { Routes } from '@angular/router';
import { gestionOriginesRoute } from './gestion-origines/gestion-origines.route';
import { gestionTypeDeBienRoute } from './gestion-typedebien/gestion-typedebien.route';
import { gestionNegociateursRoutes } from 'app/parametrage/gestion-negociateurs';
import { importerRoute } from 'app/parametrage/importer/importer.route';
import { gestionAgencesRoute } from 'app/parametrage/gestion-agences/gestion-agences.route';
import { gestionExercicesRoute } from 'app/parametrage/gestion-exercices/gestion-exercices.route';

const PARAMETRAGE_ROUTES = [
  ...gestionOriginesRoute,
  ...gestionTypeDeBienRoute,
  ...gestionNegociateursRoutes,
  ...importerRoute,
  ...gestionAgencesRoute,
  ...gestionExercicesRoute
];

export const parametrageRoutes: Routes = [
  {
    path: 'parametrage',
    children: PARAMETRAGE_ROUTES,
    data: {
      pageTitle: 'parametrage.titre'
    },
    canActivate: []
  }
];

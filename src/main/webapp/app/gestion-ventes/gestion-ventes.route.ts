import {Routes} from '@angular/router';
import {listeVentesRoute} from './liste-ventes/liste-ventes.route';
import {editerVenteRoute} from './editer-vente/editer-vente.route';


const GESTION_VENTES_ROUTES = [listeVentesRoute, ...editerVenteRoute];

export const gestionVentesRoutes: Routes = [
  {
    path: 'ventes',
    children: GESTION_VENTES_ROUTES,
    data: {
      pageTitle: 'gestion.ventes.titre',
    },
    canActivate: []
  }
];

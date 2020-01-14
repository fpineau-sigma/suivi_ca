import {Routes} from '@angular/router';
import {listeVentesRoute} from "./liste-ventes/liste-ventes.route";

const GESTION_VENTES_ROUTES = [listeVentesRoute];

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

import {Routes} from '@angular/router';
import {listeVentesRoute} from "./liste-ventes/liste-ventes.route";

const SUIVI_VENTES_ROUTES = [listeVentesRoute];

export const suiviVentesRoutes: Routes = [
  {
    path: 'ventes',
    children: SUIVI_VENTES_ROUTES,
    data: {
      pageTitle: 'suivi.ventes.titre',
    },
    canActivate: []
  }
];

import {Routes} from '@angular/router';
import {listeNegociateursRoute} from "./liste-negociateurs/liste-negociateurs.route";

const SUIVI_NEGOCIATEURS_ROUTES = [listeNegociateursRoute];

export const suiviNegociateursRoutes: Routes = [
  {
    path: 'negociateurs',
    children: SUIVI_NEGOCIATEURS_ROUTES,
    data: {
      pageTitle: 'suivi.negociateurs.titre'
    },
    canActivate: []
  }
];

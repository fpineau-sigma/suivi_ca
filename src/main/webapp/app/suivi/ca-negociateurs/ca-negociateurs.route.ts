import {Routes} from '@angular/router';
import {listeCaNegociateursRoute} from 'app/suivi/ca-negociateurs/liste-ca-negociateurs/liste-ca-negociateurs.route';

const CA_NEGOCIATEURS_ROUTES = [listeCaNegociateursRoute];

export const suiviCaNegociateursRoutes: Routes = [
  {
    path: 'ca-negociateurs',
    children: CA_NEGOCIATEURS_ROUTES,
    data: {
      pageTitle: 'suivi.ca.negociateurs.titre'
    },
    canActivate: []
  }
];

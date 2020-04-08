import {Routes} from '@angular/router';
import {caAgenceRoute} from 'app/suivi/ca-agence/ca-agence/ca-agence.route';

const CA_AGENCE_ROUTES = [caAgenceRoute];

export const suiviCaAgenceRoutes: Routes = [
  {
    path: 'ca-agence',
    children: CA_AGENCE_ROUTES,
    data: {
      pageTitle: 'suivi.ca.agence.titre'
    },
    canActivate: []
  }
];

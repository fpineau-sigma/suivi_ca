import {Routes} from '@angular/router';
import {listeCommissionsRoute} from 'app/suivi/commissions/liste-commissions/liste-commissions.route';


const SUIVI_COMMISSIONS_ROUTES = [listeCommissionsRoute];

export const suiviCommissionsRoutes: Routes = [
  {
    path: 'commissions',
    children: SUIVI_COMMISSIONS_ROUTES,
    data: {
      pageTitle: 'suivi.commissions.titre',
    },
    canActivate: []
  }
];

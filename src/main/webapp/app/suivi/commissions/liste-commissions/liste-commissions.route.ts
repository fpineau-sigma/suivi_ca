import { Route } from '@angular/router';
import { ListeCommissionsComponent } from './liste-commissions.component';

export const listeCommissionsRoute: Route = {
  path: '',
  component: ListeCommissionsComponent,
  data: {
    pageTitle: 'suivi.commissions.titre'
  },
  canActivate: []
};

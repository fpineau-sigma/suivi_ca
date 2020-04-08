import {Route} from '@angular/router';
import {CaAgenceComponent} from './ca-agence.component';

export const caAgenceRoute: Route = {
  path: '',
  component: CaAgenceComponent,
  data: {
    pageTitle: 'suivi.ca.agence.titre'
  },
  canActivate: []
};

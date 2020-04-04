import {Route} from '@angular/router';
import {ListeCaNegociateursComponent} from './liste-ca-negociateurs.component';

export const listeCaNegociateursRoute: Route = {
  path: '',
  component: ListeCaNegociateursComponent,
  data: {
    pageTitle: 'suivi.ca.negociateurs.titre'
  },
  canActivate: []
};

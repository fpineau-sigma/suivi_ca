import {Route} from '@angular/router';
import {ListeNegociateursComponent} from "./liste-negociateurs.component";

export const listeNegociateursRoute: Route = {
  path: '',
  component: ListeNegociateursComponent,
  data: {
    pageTitle: 'suivi.negociateurs.titre',
  },
  canActivate: []
};

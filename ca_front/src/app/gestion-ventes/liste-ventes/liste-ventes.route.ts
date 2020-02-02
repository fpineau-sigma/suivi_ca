import {Route} from '@angular/router';
import {ListeVentesComponent} from "./liste-ventes.component";


export const listeVentesRoute: Route = {
  path: '',
  component: ListeVentesComponent,
  data: {
    pageTitle: 'gestion.ventes.titre',
  },
  canActivate: []
};

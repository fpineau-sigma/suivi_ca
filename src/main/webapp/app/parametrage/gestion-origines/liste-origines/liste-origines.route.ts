import { Route } from '@angular/router';
import { ListeOriginesComponent } from './liste-origines.component';

export const listeOriginesRoute: Route = {
  path: '',
  component: ListeOriginesComponent,
  data: {
    pageTitle: 'gestion.origines.titre'
  },
  canActivate: []
};

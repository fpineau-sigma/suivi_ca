import { Route } from '@angular/router';
import { ListeAgencesComponent } from './liste-agences.component';

export const listeAgencesRoute: Route = {
  path: '',
  component: ListeAgencesComponent,
  data: {
    pageTitle: 'gestion.agences.titre'
  },
  canActivate: []
};

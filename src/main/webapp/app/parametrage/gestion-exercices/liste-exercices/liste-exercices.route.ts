import { Route } from '@angular/router';
import { ListeExercicesComponent } from './liste-exercices.component';

export const listeExercicesRoute: Route = {
  path: '',
  component: ListeExercicesComponent,
  data: {
    pageTitle: 'gestion.exercices.titre'
  },
  canActivate: []
};

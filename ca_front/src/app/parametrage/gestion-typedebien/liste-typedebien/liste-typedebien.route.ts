import {Route} from '@angular/router';
import {ListeTypeDeBienComponent} from './liste-typedebien.component';


export const listeTypeDeBienRoute: Route = {
  path: '',
  component: ListeTypeDeBienComponent,
  data: {
    pageTitle: 'gestion.typeDeBiens.titre',
  },
  canActivate: []
};

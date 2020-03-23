import {listeTypeDeBienRoute} from './liste-typedebien/liste-typedebien.route';
import {Routes} from '@angular/router';

const GESTION_TYPE_DE_BIEN_ROUTES = [listeTypeDeBienRoute];

export const gestionTypeDeBienRoute: Routes = [{
  path: 'typedebiens',
  children: GESTION_TYPE_DE_BIEN_ROUTES,
  data: {
    pageTitle: 'parametrage.importer.fichier.titre',
  },
  canActivate: []
}];

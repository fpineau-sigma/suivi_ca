import {Routes} from '@angular/router';
import {EditerVenteComponent} from "./editer-vente.component";

export const editerVenteRoute: Routes = [
  {
    path: 'editer-vente/:mode/:id',
    component: EditerVenteComponent,
    data: {
      pageTitle: 'gestion.ventes.titre',
    },
    canActivate: []
  },
  {
    path: 'editer-vente/:mode',
    component: EditerVenteComponent,
    data: {
      pageTitle: 'gestion.ventes.titre'
    },
    canActivate: []
  }
];

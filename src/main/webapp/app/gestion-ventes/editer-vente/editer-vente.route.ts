import {ActivatedRouteSnapshot, Resolve, Routes} from '@angular/router';
import {EditerVenteComponent} from './editer-vente.component';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {IVente, Vente} from 'app/core/model/metier/vente.model';
import {VentesService} from 'app/core/service/metier/ventes.service';

@Injectable({providedIn: 'root'})
export class EditerVenteResolve implements Resolve<IVente> {
  constructor(private service: VentesService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<IVente> {
    const id = route.params['id'];
    if (id) {
      return this.service.lire(id);
    }
    return of(new Vente());
  }
}

export const editerVenteRoute: Routes = [
  {
    path: 'editer-vente/:mode/:id',
    component: EditerVenteComponent,
    resolve: {
      user: EditerVenteResolve
    },
    data: {
      pageTitle: 'gestion.vente.modification.titre'
    },
    canActivate: []
  },
  {
    path: 'editer-vente/:mode',
    component: EditerVenteComponent,
    resolve: {
      user: EditerVenteResolve
    },
    data: {
      pageTitle: 'gestion.vente.creation.titre'
    },
    canActivate: []
  }
];

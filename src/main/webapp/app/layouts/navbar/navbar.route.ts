import {ActivatedRouteSnapshot, Resolve, Route} from '@angular/router';

import {NavbarComponent} from './navbar.component';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ExercicesService} from 'app/core/service/metier/exercices.service';
import {Exercice} from 'app/core/model/metier/exercice.model';
import {Agence} from 'app/core/model/metier/agence.model';
import {AgencesService} from 'app/core/service/metier/agences.service';

@Injectable({providedIn: 'root'})
export class EditerExerciceResolve implements Resolve<Exercice[]> {
  constructor(private exercicesService: ExercicesService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Exercice[]> {
    return this.exercicesService.lister();
  }
}

@Injectable({providedIn: 'root'})
export class EditerAgenceResolve implements Resolve<Agence[]> {
  constructor(private agencesService: AgencesService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Agence[]> {
    return this.agencesService.lister();
  }
}

export const navbarRoute: Route = {
  path: '',
  component: NavbarComponent,
  outlet: 'navbar',
  resolve: {
    agences: EditerAgenceResolve,
    exercices: EditerExerciceResolve
  },
};

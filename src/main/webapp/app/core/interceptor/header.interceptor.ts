import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';

import {Observable} from 'rxjs';
import {ClesSessionStorage} from 'app/shared/constants/storage.constants';

// tslint:disable:no-any
@Injectable({providedIn: 'root'})
export class HeaderHttpInterceptor implements HttpInterceptor {

  intercept(requeteSource: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // Construction du header générique à tous les appels
    const headers = {
      accept: 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    };

    // En cas de get sur le profil utilisateur on n'ajoute pas le header du domaine et client
    const agenceId = sessionStorage.getItem(ClesSessionStorage.AGENCE);
    const exerciceId = sessionStorage.getItem(ClesSessionStorage.EXERCICE);
    if (null !== agenceId) {
      headers[ClesSessionStorage.AGENCE] = String(agenceId);
    }
    if (null !== exerciceId) {
      headers[ClesSessionStorage.EXERCICE] = String(exerciceId);
    }
    const requeteDestination = requeteSource.clone({setHeaders: headers});
    return next.handle(requeteDestination);
  }
}

import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

// tslint:disable:no-any
@Injectable({providedIn: 'root'})
export class AngularDateHttpInterceptor implements HttpInterceptor {

  public static estDateValide(value: string): boolean {
    if (value === null || value === undefined) {
      return false;
    }

    return !isNaN(+Date.parse(value));
  }

  public static ajoutDateTimeZoneOffset(date: Date): string {
    const hoursDiff = date.getHours() - date.getTimezoneOffset() / 60;
    date.setHours(hoursDiff);

    return date.toISOString();
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let newReq = req;
    // FIXME l'intercepteur n'est pas utilisé pour psp-ged car il casse les multipart
    // à corriger le jour ou on a besoin de passer des dates du front au back de ged
    if (req.url.indexOf('fichiers') !== -1) {
      return next.handle(req);
    }
    if (['PUT', 'POST', 'PATCH'].includes(req.method)) {
      newReq = req.clone({body: this.ajoutTimeZoneDate(req.body)});
    }

    return next.handle(newReq).pipe(
      tap((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            const body = event.body;
            this.convertirEnDate(body);
          }
        }
      ));
  }

  /**
   * Fonction qui permet d'ajouter la différence d'heures du décalage horaire avant sa consersion en JSON
   * Evite d'avoir un retour du back à J-1
   * @param body contient le body de la requête
   */
  private ajoutTimeZoneDate(body: any): any {
    if (body === null || body === undefined) {
      return body;
    }

    if (typeof body !== 'object') {
      return body;
    }

    const newBody = Object.assign(Array.isArray(body) ? [] : {}, body);

    // filtre seulements les dates ou les objets
    Object.keys(newBody).filter(
      // On filtre sur les champs Date ainsi que sur les objects qui peuvent contenir des dates
      cle => newBody[cle] instanceof Date || newBody[cle] instanceof Object).forEach(cle => {
      const valeur = newBody[cle];
      if (valeur instanceof Date) {
        newBody[cle] = AngularDateHttpInterceptor.ajoutDateTimeZoneOffset(valeur);
      } else {
        this.ajoutTimeZoneDate(valeur);
      }
    });

    return newBody;
  }

  /**
   * Permet de convertir les dates qui viennent du back sous forme de string (jj-mm-annee) en date
   * @param body contient le body de la requête
   */
  private convertirEnDate(body: any): any | undefined {
    if (body === null || body === undefined) {
      return body;
    }

    if (typeof body !== 'object') {
      return body;
    }

    // filtre seulements les dates ou les objets
    Object.keys(body).filter(
      cle => cle.startsWith('date')
        && AngularDateHttpInterceptor.estDateValide(body[cle])
        || typeof body[cle] === 'object').forEach(cle => {
      const valeur = body[cle];
      if (typeof valeur === 'string') {
        body[cle] = new Date(valeur);
      } else if (typeof valeur === 'object') {
        this.convertirEnDate(valeur);
      }
    });
  }
}

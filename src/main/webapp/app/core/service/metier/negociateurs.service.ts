import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Negociateur } from '../../model/metier/negociateur.model';
import { SERVER_API_URL } from 'app/app.constants';

const url = SERVER_API_URL + 'api/negociateurs';

@Injectable({ providedIn: 'root' })
export class NegociateursService {
  constructor(private http: HttpClient) {}

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }

  enregistrer(negociateur: Negociateur): Observable<any> {
    return this.http.post<Negociateur>(`${url}`, negociateur);
  }

  modifier(negociateur: Negociateur): Observable<any> {
    const id = negociateur.id;
    return this.http.put<Negociateur>(`${url}/${id}`, negociateur);
  }
}

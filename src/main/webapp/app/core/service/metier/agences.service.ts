import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Agence } from '../../model/metier/agence.model';
import { SERVER_API_URL } from 'app/app.constants';

const url = SERVER_API_URL + 'api/agences';

@Injectable({ providedIn: 'root' })
export class AgencesService {
  constructor(private http: HttpClient) {}

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }

  enregistrer(agence: Agence): Observable<any> {
    return this.http.post<Agence>(`${url}`, agence);
  }

  modifier(agence: Agence): Observable<any> {
    const id = agence.id;
    return this.http.put<Agence>(`${url}/${id}`, agence);
  }
}

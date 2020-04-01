import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exercice } from '../../model/metier/exercice.model';
import { SERVER_API_URL } from 'app/app.constants';

const url = SERVER_API_URL + 'api/exercices';

@Injectable({ providedIn: 'root' })
export class ExercicesService {
  constructor(private http: HttpClient) {}

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }

  enregistrer(exercice: Exercice): Observable<any> {
    return this.http.post<Exercice>(`${url}`, exercice);
  }

  modifier(exercice: Exercice): Observable<any> {
    const id = exercice.id;
    return this.http.put<Exercice>(`${url}/${id}`, exercice);
  }
}

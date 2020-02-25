import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Origine} from '../model/origine.model';


const url = '//localhost:8090/origines';

@Injectable({providedIn: 'root'})
export class OriginesService {

  constructor(private http: HttpClient) {
  }

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }

  enregistrer(origine: Origine): Observable<any> {
    return this.http.post<Origine>(`${url}`, origine);
  }

  modifier(origine: Origine): Observable<any> {
    const id = origine.id;
    return this.http.put<Origine>(`${url}/${id}`, origine);
  }
}

import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {Vente} from '../../model/vente.model';
import {SERVER_API_URL} from 'app/app.constants';


const url = SERVER_API_URL + 'api/ventes';

@Injectable({
  providedIn: 'root'
})
export class VentesService {

  constructor(private http: HttpClient) {
  }

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }

  lire(id: number): Observable<any> {
    return this.http.get(`${url}/${id}`);
  }

  enregistrer(vente: Vente): Observable<any> {
    return this.http.post<Vente>(`${url}`, vente);
  }

  modifier(vente: Vente): Observable<any> {
    const id = vente.id;
    return this.http.put<Vente>(`${url}/${id}`, vente);
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeDeBien} from '../../model/typedebien.model';
import {SERVER_API_URL} from 'app/app.constants';


const url = SERVER_API_URL + 'api/typedebiens';

@Injectable({providedIn: 'root'})
export class TypeDeBiensService {

  constructor(private http: HttpClient) {
  }

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }

  enregistrer(typeDeBien: TypeDeBien): Observable<any> {
    return this.http.post<TypeDeBien>(`${url}`, typeDeBien);
  }

  modifier(typeDeBien: TypeDeBien): Observable<any> {
    const id = typeDeBien.id;
    return this.http.put<TypeDeBien>(`${url}/${id}`, typeDeBien);
  }
}

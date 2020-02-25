import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeDeBien} from '../model/typeDeBien.model';


const url = '//localhost:8090/typedebiens';

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

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from 'app/app.constants';

const url = SERVER_API_URL + 'api/objectifs';

@Injectable({providedIn: 'root'})
export class ObjectifService {
  constructor(private http: HttpClient) {
  }

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }
}

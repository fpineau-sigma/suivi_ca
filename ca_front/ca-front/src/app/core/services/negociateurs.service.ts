import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class NegociateursService {

  constructor(private http: HttpClient) { }

  lister(): Observable<any> {
    return this.http.get('//localhost:8090/negociateurs/');
  }
}

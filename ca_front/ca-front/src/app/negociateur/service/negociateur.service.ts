import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Negociateur } from '../../dto/negociateur';

@Injectable({providedIn: 'root'})
export class NegociateurService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8090/negociateurs/');
  }
}

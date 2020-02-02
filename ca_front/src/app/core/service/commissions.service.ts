import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const url = '//localhost:8090/commissions';

@Injectable()
export class CommissionsService {

  constructor(private http: HttpClient) {
  }

  lister(): Observable<any> {
    return this.http.get(`${url}`);
  }
}

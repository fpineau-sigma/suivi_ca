import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from 'app/app.constants';
import {Page} from 'app/core/model/pagination/page.model';
import {createRequestOption} from 'app/shared/util/request-util';
import {CriteresRechercheCommission} from 'app/core/model/criteres.recherche/criteresRechercheCommission.model';
import {Commission} from 'app/core/model/metier/commission.model';

const url = SERVER_API_URL + 'api/commissions';

@Injectable()
export class CommissionsService {

  constructor(private http: HttpClient) {
  }

  lister(criteresRechercheCommission: CriteresRechercheCommission, req: any): Observable<HttpResponse<Page<Commission[]>>> {
    const params: HttpParams = createRequestOption(req);

    return this.http.post<Page<Commission[]>>(`${url}/lister`, criteresRechercheCommission, {
      params,
      observe: 'response'
    });
  }
}

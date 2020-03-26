import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {Vente} from '../../model/metier/vente.model';
import {SERVER_API_URL} from 'app/app.constants';
import {CriteresRechercheVente} from 'app/core/model/criteres.recherche/criteresRechercheVente.model';
import {createRequestOption} from 'app/shared/util/request-util';
import {Page} from 'app/core/model/pagination/page.model';


const url = SERVER_API_URL + 'api/ventes';

@Injectable({
  providedIn: 'root'
})
export class VentesService {

  constructor(private http: HttpClient) {
  }

  lister(criteresRechercheVente: CriteresRechercheVente, req: any): Observable<HttpResponse<Page<Vente[]>>> {
    const params: HttpParams = createRequestOption(req);

    return this.http.post<Page<Vente[]>>(`${url}/lister`, criteresRechercheVente, {
      params,
      observe: 'response'
    });
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

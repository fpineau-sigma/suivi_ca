import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { VenteParMois } from 'app/core/model/visualisation/venteparmois.model';

const url = SERVER_API_URL + 'api/visualisation/ventes';

@Injectable({
  providedIn: 'root'
})
export class VisualisationVentesService {
  constructor(private http: HttpClient) {}

  venteParMois(): Observable<VenteParMois[]> {
    return this.http.get<VenteParMois[]>(`${url}`);
  }
}

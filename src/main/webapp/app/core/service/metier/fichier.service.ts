import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {SERVER_API_URL} from 'app/app.constants';


const url = SERVER_API_URL + 'api/fichiers';

@Injectable({
  providedIn: 'root'
})
export class FichierService {

  constructor(private http: HttpClient) {
  }

  envoyerDonnees(fichier: File): Observable<any> {
    const formData = new FormData();
    formData.append('fichier', fichier);

    return this.http.post<File>(`${url}`, formData);
  }

}

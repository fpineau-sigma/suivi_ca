import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';


const url = '//localhost:8090/fichiers';

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

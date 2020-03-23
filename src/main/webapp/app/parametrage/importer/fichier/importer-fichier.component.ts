import {Component} from '@angular/core';
import {FichierService} from '../../../core/service/application/fichier.service';
import {ToastService} from '../../../core/service/toast.service';


@Component({
  selector: 'jhi-importer-fichier',
  templateUrl: './importer-fichier.component.html'
})
export class ImporterFichierComponent {

  public nomFichier: string;
  public fichier!: File;

  constructor(
    private fichierService: FichierService,
    private toastService: ToastService
  ) {
  }

  /**
   * Fonction qui permet d'importer un fichier
   */
  public importerFichier(): void {
    if (null !== this.fichier) {
      this.fichierService.envoyerDonnees(this.fichier).subscribe(() => {
        this.toastService.success('gestion.negociateur.action.success');
      });
    }
  }

  /**
   * Fonction qui permet de charger un fichier
   * @param event fichier à importer
   */
  public chargementFichier(event: any): void {
    // when the load event is fired and the file not empty
    if (event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.fichier = event.target.files[0];
    }
  }

}

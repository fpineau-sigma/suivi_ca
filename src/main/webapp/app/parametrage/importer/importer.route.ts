import {Routes} from '@angular/router';
import {importerFichierRoute} from 'app/parametrage/importer/fichier/importer-fichier.route';

const IMPORTER_ROUTES = [importerFichierRoute];

export const importerRoute: Routes = [{
  path: 'importdonnees',
  children: IMPORTER_ROUTES,
  data: {
    pageTitle: 'parametrage.importer.fichier.titre',
  },
  canActivate: []
}];

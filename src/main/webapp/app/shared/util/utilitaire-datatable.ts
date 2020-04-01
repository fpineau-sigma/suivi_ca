import { HttpParams } from '@angular/common/http';
import { Tri } from 'app/core/model/pagination/tri.model';

/**
 * Prépare le tri pour être compatible avec l'objet @Pageable de Spring
 * @param tris
 * tableau de tris générés par ngx-datatable
 */
export const preparerTriPourServeur = (tris?: Tri[]): HttpParams => {
  let triTransforme;
  if (tris) {
    triTransforme = tris.map(tri => tri.prop + ',' + tri.dir);
  }

  return triTransforme;
};

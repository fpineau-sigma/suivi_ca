/**
 * Objet pour gérer la pagination venant du serveur.
 */
export class Page<T> {
  // Le nombre d'éléments dans la page
  size = 0;
  // Le nombre total d'éléments
  totalElements = 0;
  // Le nombre de pages au total
  totalPages = 0;
  // Le numéro de la page courante
  number = 0;
  // Le contenu
  content: T;
}

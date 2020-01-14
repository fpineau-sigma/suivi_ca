import {ModalOptions} from 'ngx-bootstrap';

/**
 * Options par défaut pour désactiver la sortie de la modal en cliquant en dehors de la fenêtre et avec les touches du clavier
 */
export class ConfigModalCommun extends ModalOptions {
  constructor() {
    super();
    this.ignoreBackdropClick = true;
    this.keyboard = false;
    this.backdrop = 'static';
  }
}

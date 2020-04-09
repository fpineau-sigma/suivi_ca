import {Injectable} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

import {LoginModalComponent} from 'app/shared/login/login.component';
import {RefreshService} from 'app/core/service/refresh.service';

@Injectable({providedIn: 'root'})
export class LoginModalService {
  private isOpen = false;

  constructor(private modalService: NgbModal,
              private refreshService: RefreshService) {
  }

  open(): void {
    if (this.isOpen) {
      return;
    }
    this.isOpen = true;
    const modalRef: NgbModalRef = this.modalService.open(LoginModalComponent);
    modalRef.result.finally(() => {
      this.isOpen = false;
      this.refreshService.refresh();
    });
  }
}

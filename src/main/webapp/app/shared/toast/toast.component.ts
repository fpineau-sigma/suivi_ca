import {Component, OnDestroy, OnInit} from '@angular/core';

import {Subscription, timer} from 'rxjs';
import {ToastService} from '../../core/service/toast.service';
import {DelaisParType, optionToastDefault, ToastApplicative, ToastLevelEnum} from './toast.model';

@Component({
  selector: 'jhi-pms-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss']
})
export class ToastComponent implements OnInit, OnDestroy {
  public toasts: ToastApplicative[] = [];

  private readonly subscriptions: Subscription[] = [];

  constructor(private toastService: ToastService) {
  }

  public ngOnInit(): void {
    this.subscriptions.push(this.toastService.subjectToast.subscribe((toast: ToastApplicative) => {
      const options = Object.assign({}, optionToastDefault);
      Object.assign(options, toast.options);
      toast.options = options;
      this.ajouterToast(toast);
      if (DelaisParType[toast.code] > 0) {
        this.subscriptions.push(timer(DelaisParType[toast.code]).subscribe(() => this.closeToast(toast)));
      }
    }));
  }

  ajouterToast(toast: ToastApplicative): void {
    this.toasts.push(toast);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      if (subscription) {
        subscription.unsubscribe();
      }
    });
  }

  closeToast(toast: ToastApplicative): void {
    this.toasts.splice(this.toasts.indexOf(toast), 1);
  }

  closeAll(): void {
    this.toasts.splice(0, this.toasts.length);
  }

  getToastLevelEnum(): typeof ToastLevelEnum {
    return ToastLevelEnum;
  }
}

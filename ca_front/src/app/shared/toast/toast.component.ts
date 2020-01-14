import {Component, OnDestroy, OnInit} from '@angular/core';

import {Subscription, timer} from 'rxjs';
import {ToastService} from "../../core/service/toast.service";
import {DelaisParType, optionToastDefault, ToastApplicative, ToastLevelEnum} from "./toast.model";

@Component({
  selector: 'app-pms-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss']
})
export class ToastComponent implements OnInit, OnDestroy {
  public toasts: ToastApplicative[] = [];

  private readonly subscriptions: Subscription[] = [];

  constructor(private readonly toastService: ToastService) {
  }

  ngOnInit() {
    this.subscriptions.push(this.toastService.subjectToast.subscribe(toast => {
      const options = Object.assign({}, optionToastDefault);
      Object.assign(options, toast.options);
      toast.options = options;
      this.ajouterToast(toast);
      if (DelaisParType[toast.code] > 0) {
        this.subscriptions.push(timer(DelaisParType[toast.code]).subscribe(() => this.closeToast(toast)));
      }
    }));
  }

  ajouterToast(toast: ToastApplicative) {
    this.toasts.push(toast);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      if (!!subscription) {
        subscription.unsubscribe();
      }
    });
  }

  closeToast(toast) {
    this.toasts.splice(this.toasts.indexOf(toast), 1);
  }

  closeAll() {
    this.toasts.splice(0, this.toasts.length);
  }

  getToastLevelEnum() {
    return ToastLevelEnum;
  }
}

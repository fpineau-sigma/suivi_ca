import {Injectable, OnDestroy} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {ReplaySubject, Subscription} from 'rxjs';
import {
  OptionToast,
  ToastApplicative,
  ToastLevelEnum,
  toasts
} from '../../shared/toast/toast.model';


// tslint:disable:no-any
@Injectable({
  providedIn: 'root'
})
export class ToastService implements OnDestroy {
  private toastId: number;
  private readonly subjectCurrentToast: ReplaySubject<any> = new ReplaySubject<any>(1);
  private readonly subscriptions: Subscription[] = [];

  constructor(private translateService: TranslateService) {
    this.toastId = 0;
  }

  get subjectToast(): ReplaySubject<ToastApplicative> {
    return this.subjectCurrentToast;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      if (null !== subscription) {
        subscription.unsubscribe();
      }
    });
  }

  /**
   * Affiche un message temporaire d'alerte a l'utilisateur en bas de l'ecran
   *
   * @param message un message sous forme de texte. accepte des paramètres. ex: 'Un message avec un parametre: {{ param }}'
   * @param params le dictionnaire de paramètres pour l'objer. ex: '{"param": "test"}'
   * @param options les options du toast.
   */
  warning(message: string, params?: any, options?: OptionToast): void {
    this.toast(ToastLevelEnum.WARNING, message, params, options);
  }

  /**
   * Affiche un message temporaire d'information a l'utilisateur en bas de l'ecran
   *
   * @param message un message sous forme de texte. accepte des paramètres. ex: 'Un message avec un parametre: {{ param }}'
   * @param params le dictionnaire de paramètres pour l'objer. ex: '{"param": "test"}'
   * @param options les options du toast.
   */
  info(message: string, params?: any, options?: OptionToast): void {
    this.toast(ToastLevelEnum.INFO, message, params, options);
  }

  /**
   * Affiche un message temporaire de succes a l'utilisateur en bas de l'ecran
   *
   * @param message un message sous forme de texte. accepte des paramètres. ex: 'Un message avec un parametre: {{ param }}'
   * @param params le dictionnaire de paramètres pour l'objer. ex: '{"param": "test"}'
   * @param options les options du toast.
   */
  success(message: string, params?: any, options?: OptionToast): void {
    this.toast(ToastLevelEnum.SUCCESS, message, params, options);
  }

  /**
   * Affiche un message temporaire d'erreur a l'utilisateur en bas de l'ecran
   *
   * @param message un message sous forme de texte. accepte des paramètres. ex: 'Un message avec un parametre: {{ param }}'
   * @param params le dictionnaire de paramètres pour l'objer. ex: '{"param": "test"}'
   * @param options les options du toast.
   */
  error(message: string, params?: any, options?: OptionToast): void {
    this.toast(ToastLevelEnum.ERROR, message, params, options);
  }

  traiterParams(message: string, params?: any): string {
    if (params === undefined || params === null) {
      return message;
    }
    const values = (Object.values(params) as string[]);
    Object.keys(params).forEach((k, i) => {
      message = message.replace('{{' + k + '}}', values[i]);
    });

    return message;
  }

  private toast(level: ToastLevelEnum, message: string, params?: any, options?: OptionToast): void {
    const toast: ToastApplicative = Object.assign({}, toasts.find((n) => n.code === level));
    toast.id = this.toastId++;
    if (this.translateService) {
      this.subscriptions.push(this.translateService.get(message, params).subscribe((messageTraduit) => {
        toast.message = messageTraduit;
        toast.options = options;
        this.subjectToast.next(toast);
      }));
    } else {
      toast.message = this.traiterParams(message, params);
      toast.options = options;
      this.subjectToast.next(toast);
    }
  }
}

export enum ToastLevelEnum {
  ERROR = 'ERROR',
  WARNING = 'WARNING',
  INFO = 'INFO',
  SUCCESS = 'SUCCESS'
}

export const DelaisParType = {
  [ToastLevelEnum.ERROR]: 10000,
  [ToastLevelEnum.INFO]: 5000,
  [ToastLevelEnum.WARNING]: 5000,
  [ToastLevelEnum.SUCCESS]: 5000
};

export interface OptionToast {
  title: boolean;
}

export const optionToastDefault: OptionToast = {
  title: true
};

export class Toast {
  constructor(public code?: ToastLevelEnum,
              public libelle?: string,
              public level?: string) {
  }
}

export class ToastApplicative extends Toast {
  constructor(public id?: number,
              public message?: string,
              public options?: OptionToast) {
    super();
  }
}

export const toasts: Toast[] = [
  new Toast(ToastLevelEnum.ERROR, 'global.ihm.toast.error',
    ToastLevelEnum.ERROR.toLowerCase()),
  new Toast(ToastLevelEnum.WARNING, 'global.ihm.toast.warning',
    ToastLevelEnum.WARNING.toLowerCase()),
  new Toast(ToastLevelEnum.INFO, 'global.ihm.toast.info',
    ToastLevelEnum.INFO.toLowerCase()),
  new Toast(ToastLevelEnum.SUCCESS, 'global.ihm.toast.success',
    ToastLevelEnum.SUCCESS.toLowerCase())
];

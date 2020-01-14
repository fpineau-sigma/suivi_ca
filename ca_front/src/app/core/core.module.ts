import {registerLocaleData} from '@angular/common';
// tslint:disable-next-line:match-default-export-name
import locale from '@angular/common/locales/fr';
import {LOCALE_ID, NgModule} from '@angular/core';
import {BsLocaleService, defineLocale, frLocale} from 'ngx-bootstrap';
import {LangageAssistant} from "./language/langage-assistant.service";

@NgModule({
  exports: [],
  declarations: [],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'fr'
    },
    LangageAssistant
  ]
})
export class CoreModule {
  constructor(private readonly localeService: BsLocaleService) {
    registerLocaleData(locale);
    defineLocale('fr', frLocale);
    this.localeService.use('fr');
  }
}

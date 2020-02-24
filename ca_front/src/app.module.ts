import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AccueilComponent} from './app/layouts/accueil/accueil.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {NavbarComponent} from './app/layouts/navbar/navbar.component';
import {Page403Component} from './app/layouts/page-erreur/page403/page403.component';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {GestionVentesModule} from './app/gestion-ventes/gestion-ventes.module';
import {GestionNegociateursModule} from './app/gestion-negociateurs/gestion-negociateurs.module';
import {SharedModule} from './app/shared/shared.module';
import {BsDatepickerConfig, BsDatepickerModule, BsDropdownModule} from 'ngx-bootstrap';
import {AngularDateHttpInterceptor} from './app/core/interceptor/angular-date.interceptor';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {SuiviCommissionsModule} from './app/suivi-commissions/suivi-commissions.module';
import {ParametrageModule} from './app/parametrage/parametrage.module';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/');
}

export function getDatepickerConfig(): BsDatepickerConfig {
  return Object.assign(new BsDatepickerConfig(), {
    containerClass: 'theme-dark-blue',
    adaptivePosition: true
  });
}

@NgModule({
  declarations: [
    AccueilComponent,
    NavbarComponent,
    Page403Component
  ],
  imports: [
    SharedModule.forRoot(),
    BsDropdownModule.forRoot(),
    BrowserAnimationsModule,
    GestionVentesModule,
    GestionNegociateursModule,
    SuiviCommissionsModule,
    ParametrageModule,
    HttpClientModule,
    AppRoutingModule,
    FontAwesomeModule,
    BsDatepickerModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  providers: [
    {provide: BsDatepickerConfig, useFactory: getDatepickerConfig},
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AngularDateHttpInterceptor,
      multi: true
    },
  ],
  bootstrap: [AccueilComponent]
})
export class AppModule {
}

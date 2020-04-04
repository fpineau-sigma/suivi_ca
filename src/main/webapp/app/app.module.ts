import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import './vendor';
import {CaClemenceSharedModule} from 'app/shared/shared.module';
import {CaClemenceCoreModule} from 'app/core/core.module';
import {CaClemenceAppRoutingModule} from './app-routing.module';
import {CaClemenceHomeModule} from './home/home.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {MainComponent} from './layouts/main/main.component';
import {NavbarComponent} from './layouts/navbar/navbar.component';
import {FooterComponent} from './layouts/footer/footer.component';
import {PageRibbonComponent} from './layouts/profiles/page-ribbon.component';
import {ActiveMenuDirective} from './layouts/navbar/active-menu.directive';
import {ErrorComponent} from './layouts/error/error.component';
import {ParametrageModule} from 'app/parametrage/parametrage.module';
import {GestionVentesModule} from 'app/gestion-ventes/gestion-ventes.module';
import {SuiviCommissionsModule} from 'app/suivi/commissions/suivi-commissions.module';
import {BsDatepickerConfig, BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {AngularDateHttpInterceptor} from 'app/core/interceptor/angular-date.interceptor';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderHttpInterceptor} from 'app/core/interceptor/header.interceptor';
import {SuiviCaNegociateursModule} from 'app/suivi/ca-negociateurs/ca-negociateurs.module';

export function getDatepickerConfig(): BsDatepickerConfig {
  return Object.assign(new BsDatepickerConfig(), {
    containerClass: 'theme-dark-blue',
    adaptivePosition: true,
    dateInputFormat: 'DD/MM/YYYY'
  });
}

@NgModule({
  imports: [
    BrowserModule,
    CaClemenceSharedModule,
    BrowserAnimationsModule,
    CaClemenceCoreModule,
    CaClemenceHomeModule,
    ParametrageModule,
    GestionVentesModule,
    SuiviCommissionsModule,
    SuiviCaNegociateursModule,
    BsDatepickerModule.forRoot(),
    // jhipster-needle-angular-add-module JHipster will add new module here
    CaClemenceAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  providers: [
    {provide: BsDatepickerConfig, useFactory: getDatepickerConfig},
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HeaderHttpInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AngularDateHttpInterceptor,
      multi: true
    }
  ],
  bootstrap: [MainComponent]
})
export class CaClemenceAppModule {
}

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AccueilComponent} from './app/layouts/accueil/accueil.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {NegociateursService} from './app/core/services/negociateurs.service';
import {CommissionsService} from './app/core/services/commissions.service';
import {NavbarComponent} from "./app/layouts/navbar/navbar.component";
import {Page403Component} from "./app/layouts/page-erreur/page403/page403.component";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {GestionVentesModule} from "./app/gestion-ventes/gestion-ventes.module";
import {GestionNegociateursModule} from "./app/gestion-negociateurs/gestion-negociateurs.module";
import {CommonModule} from "@angular/common";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/');
}

@NgModule({
  declarations: [
    AccueilComponent,
    NavbarComponent,
    Page403Component
  ],
  imports: [
    BrowserModule,
    CommonModule,
    BrowserAnimationsModule,
    GestionVentesModule,
    GestionNegociateursModule,
    HttpClientModule,
    AppRoutingModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  providers: [CommissionsService, NegociateursService],
  bootstrap: [AccueilComponent]
})
export class AppModule { }

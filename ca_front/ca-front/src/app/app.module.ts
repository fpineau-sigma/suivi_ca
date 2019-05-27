import { MatButtonModule, MatCardModule, MatTableModule, MatInputModule,
  MatListModule, MatToolbarModule, MatMenuModule, MatIconModule, MatPaginatorModule, } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VentesComponent } from './ventes/ventes.component';
import { CommissionsComponent } from './commissions/commissions.component';
import { NegociateursComponent } from './negociateurs/negociateurs.component';
import { AccueilComponent } from './accueil/accueil.component';
import { HttpClientModule } from '@angular/common/http';
import { NegociateursService } from './negociateurs/negociateurs.service';
import { CommissionsService } from './commissions/commissions.service';

@NgModule({
  declarations: [
    AppComponent,
    CommissionsComponent,
    NegociateursComponent,
    VentesComponent,
    AccueilComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatTableModule,
    MatPaginatorModule,
    AppRoutingModule
  ],
  providers: [CommissionsService, NegociateursService],
  bootstrap: [AppComponent]
})
export class AppModule { }
import { MatButtonModule, MatCardModule, MatTableModule, MatInputModule, MatListModule, MatToolbarModule, MatMenuModule, MatIconModule, MatPaginatorModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommissionComponent } from './commission/commission.component';
import { CommissionService } from './commission/service/commission.service';
import { HttpClientModule } from '@angular/common/http';
import { NegociateurComponent } from './negociateur/negociateur.component';
import { NegociateurService } from './negociateur/service/negociateur.service';



@NgModule({
  declarations: [
    AppComponent,
    CommissionComponent,
    NegociateurComponent
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
  providers: [CommissionService, NegociateurService],
  bootstrap: [AppComponent]
 })
 export class AppModule { }


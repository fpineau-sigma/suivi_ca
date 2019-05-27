import { AccueilComponent } from './accueil/accueil.component';
import { VentesComponent } from './ventes/ventes.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NegociateursComponent } from './negociateurs/negociateurs.component';
import { CommissionsComponent } from './commissions/commissions.component';


const routes: Routes = [
  { path: 'negociateurs', component: NegociateursComponent },
  { path: 'commissions', component: CommissionsComponent },
  { path: 'ventes', component: VentesComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: '', redirectTo: '/accueil', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes,
    { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

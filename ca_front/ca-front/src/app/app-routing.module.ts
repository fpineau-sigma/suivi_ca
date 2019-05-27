import { VentesComponent } from './ventes/ventes.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NegociateurComponent } from './negociateur/negociateur.component';
import { CommissionComponent } from './commission/commission.component';


const routes: Routes = [
  { path: 'negociateurs', component: NegociateurComponent },
  { path: 'commissions', component: CommissionComponent },
  { path: 'ventes', component: VentesComponent },
  { path: '', redirectTo: '/ventes', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes,
    { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

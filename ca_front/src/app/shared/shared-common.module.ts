import {NgModule} from '@angular/core';

import {RouterModule} from '@angular/router';
import {SharedLibsModule} from './shared-libs.module';
import {AdresseComponent} from './adresse/adresse.component';
import {ToastComponent} from './toast/toast.component';
import {PersonneComponent} from './personne/personne.component';

@NgModule({
  imports: [SharedLibsModule, RouterModule],
  declarations: [ToastComponent, AdresseComponent, PersonneComponent],
  exports: [SharedLibsModule, ToastComponent, AdresseComponent, PersonneComponent]
})
export class SharedCommonModule {
}

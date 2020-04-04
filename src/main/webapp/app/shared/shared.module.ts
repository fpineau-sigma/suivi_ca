import {NgModule} from '@angular/core';
import {CaClemenceSharedLibsModule} from './shared-libs.module';
import {FindLanguageFromKeyPipe} from './language/find-language-from-key.pipe';
import {AlertComponent} from './alert/alert.component';
import {AlertErrorComponent} from './alert/alert-error.component';
import {LoginModalComponent} from './login/login.component';
import {HasAnyAuthorityDirective} from './auth/has-any-authority.directive';
import {AccordionModule, BsModalService, ModalModule, PopoverModule} from 'ngx-bootstrap';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {NgSelectModule} from '@ng-select/ng-select';
import {ToastComponent} from 'app/shared/toast/toast.component';
import {RefreshService} from 'app/core/service/refresh.service';
import {PersonneComponent} from 'app/shared/personne/personne.component';
import {AdresseComponent} from 'app/shared/adresse/adresse.component';

@NgModule({
  imports: [CaClemenceSharedLibsModule, ModalModule.forRoot(), AccordionModule.forRoot()],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    ToastComponent,
    HasAnyAuthorityDirective,
    PersonneComponent,
    AdresseComponent
  ],
  entryComponents: [LoginModalComponent, ToastComponent],
  exports: [
    CaClemenceSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    PopoverModule,
    NgxDatatableModule,
    NgSelectModule,
    AdresseComponent,
    ToastComponent
  ],
  providers: [RefreshService, BsModalService]
})
export class CaClemenceSharedModule {
}

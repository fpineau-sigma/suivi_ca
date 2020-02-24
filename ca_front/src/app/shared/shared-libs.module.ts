import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgSelectModule} from '@ng-select/ng-select';
import {TranslateModule} from '@ngx-translate/core';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {BsDatepickerModule, CollapseModule, PopoverModule} from 'ngx-bootstrap';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [BrowserAnimationsModule, TranslateModule, NgSelectModule],
  exports: [FormsModule, CommonModule, CollapseModule, BsDatepickerModule,
    TranslateModule, PopoverModule, NgxDatatableModule,
    NgSelectModule, FontAwesomeModule]
})
export class SharedLibsModule {
  static forRoot() {
    return {
      ngModule: SharedLibsModule
    };
  }
}

import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgSelectModule} from '@ng-select/ng-select';
import {TranslateModule} from '@ngx-translate/core';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {BsDatepickerModule, BsDropdownModule, CollapseModule, PopoverModule} from 'ngx-bootstrap';

@NgModule({
  imports: [BrowserAnimationsModule, TranslateModule, NgSelectModule],
  exports: [FormsModule, CommonModule, CollapseModule, BsDatepickerModule,
    TranslateModule, PopoverModule, BsDropdownModule, NgxDatatableModule,
    NgSelectModule]
})
export class SharedLibsModule {
  static forRoot() {
    return {
      ngModule: SharedLibsModule
    };
  }
}

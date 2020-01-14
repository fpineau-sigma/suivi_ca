import {NgModule} from '@angular/core';

import {RouterModule} from '@angular/router';
import {SharedLibsModule} from "./shared-libs.module";
import {ToastComponent} from "./toast/toast.component";

@NgModule({
  imports: [SharedLibsModule, RouterModule],
  declarations: [ToastComponent],
  exports: [SharedLibsModule, ToastComponent]
})
export class SharedCommonModule {
}

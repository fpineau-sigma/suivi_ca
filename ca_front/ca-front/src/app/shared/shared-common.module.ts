import {NgModule} from '@angular/core';

import {RouterModule} from '@angular/router';
import {SharedLibsModule} from "./shared-libs.module";

@NgModule({
  imports: [SharedLibsModule, RouterModule],
  declarations: [],
  exports: [SharedLibsModule]
})
export class SharedCommonModule {
}

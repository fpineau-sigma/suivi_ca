import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {environment} from './environments/environment';
import {navbarRoute} from './app/layouts';
import {page403Route} from './app/layouts/page-erreur/page403/page403.route';

const DEBUG_INFO_ENABLED = !environment;
const LAYOUT_ROUTES = [navbarRoute, page403Route];

@NgModule({
  imports: [
    RouterModule.forRoot(
      LAYOUT_ROUTES,
      {
        enableTracing: DEBUG_INFO_ENABLED,
        useHash: true,
        scrollPositionRestoration: 'enabled',
        anchorScrolling: 'enabled'
      })
  ],
  exports: [RouterModule]
})

export class AppRoutingModule {
}

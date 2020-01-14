import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRouteSnapshot, NavigationEnd, NavigationError, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {TranslateService} from "@ngx-translate/core";
import {LangageAssistant} from "../../core/language/langage-assistant.service";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html'
})
export class AccueilComponent implements OnInit, OnDestroy {

  private readonly subscriptions: Subscription[] = [];

  constructor(
    private readonly langageAssistant: LangageAssistant,
    public readonly translate: TranslateService,
    private readonly titleService: Title,
    private readonly router: Router) {
    translate.addLangs(['en', 'fr']);
    translate.setDefaultLang('fr');

    const browserLang = translate.getBrowserLang();
    translate.use(browserLang.match(/en|fr/) ? browserLang : 'fr');
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      if (null !== subscription) {
        subscription.unsubscribe();
      }
    });
  }

  ngOnInit() {
    this.subscriptions.push(this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.langageAssistant.majTitre(this.getPageTitle(this.router.routerState.snapshot.root));
      }
      if (event instanceof NavigationError && event.error.status === 404) {
        this.router.navigate(['/404']);
      }
    }));
  }

  private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
    let title: string = routeSnapshot.data && routeSnapshot.data.pageTitle ? routeSnapshot.data.pageTitle : 'global.title';
    if (routeSnapshot.firstChild) {
      title = this.getPageTitle(routeSnapshot.firstChild) || title;
    }

    return title;
  }
}

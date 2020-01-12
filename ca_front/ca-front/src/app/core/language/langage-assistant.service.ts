import {Injectable, OnDestroy, Renderer2, RendererFactory2} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {BehaviorSubject, Subscription} from 'rxjs';

@Injectable({providedIn: 'root'})
export class LangageAssistant implements OnDestroy {
  renderer: Renderer2 = null;
  private readonly language: BehaviorSubject<string>;
  private readonly subscriptions: Subscription[] = [];

  constructor(
      private readonly translateService: TranslateService,
      private readonly titleService: Title,
      private readonly router: Router,
      rootRenderer: RendererFactory2
  ) {
    this.language = new BehaviorSubject<string>(this.translateService.currentLang);
    this.renderer = rootRenderer.createRenderer(document.querySelector('html'), null);
    this.init();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      if (null !== subscription) {
        subscription.unsubscribe();
      }
    });
  }

  /**
   * Mettre à jour le titre de la fenêtre en utilisant les paramètres dans l'ordre suivant :
   * order:
   * 1. cleTitre
   * 2. $state.$current.data.pageTitle (current state page title)
   * 3. 'global.title'
   */
  majTitre(cleTitre?: string) {
    if (!cleTitre) {
      cleTitre = this.lireTitrePage(this.router.routerState.snapshot.root);
    }

    this.subscriptions.push(this.translateService.get(cleTitre).subscribe(title => {
      this.titleService.setTitle(title);
    }));
  }

  private init() {
    this.subscriptions.push(this.translateService.onLangChange.subscribe(() => {
      this.language.next(this.translateService.currentLang);
      this.renderer.setAttribute(document.querySelector('html'), 'lang', this.translateService.currentLang);
      this.majTitre();
    }));
  }

  private lireTitrePage(routeSnapshot: ActivatedRouteSnapshot) {
    let title: string = routeSnapshot.data && routeSnapshot.data.pageTitle ? routeSnapshot.data.pageTitle : 'global.title';
    if (routeSnapshot.firstChild) {
      title = this.lireTitrePage(routeSnapshot.firstChild) || title;
    }

    return title;
  }
}

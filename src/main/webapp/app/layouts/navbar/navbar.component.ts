import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {JhiLanguageService} from 'ng-jhipster';
import {SessionStorageService} from 'ngx-webstorage';

import {VERSION} from 'app/app.constants';
import {LANGUAGES} from 'app/core/language/language.constants';
import {AccountService} from 'app/core/auth/account.service';
import {LoginModalService} from 'app/core/login/login-modal.service';
import {LoginService} from 'app/core/login/login.service';
import {ProfileService} from 'app/layouts/profiles/profile.service';
import {AgencesService} from 'app/core/service/metier/agences.service';
import {Agence} from 'app/core/model/metier/agence.model';
import {Exercice} from 'app/core/model/metier/exercice.model';
import {ExercicesService} from 'app/core/service/metier/exercices.service';
import {ClesSessionStorage} from 'app/shared/constants/storage.constants';
import {RefreshService} from 'app/core/service/refresh.service';

@Component({
  selector: 'jhi-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['navbar.scss']
})
export class NavbarComponent implements OnInit {
  inProduction?: boolean;
  isNavbarCollapsed = true;
  languages = LANGUAGES;
  swaggerEnabled?: boolean;
  version: string;

  public agences: Agence[] = [];
  public agenceSelectionnee: Agence;
  public exercices: Exercice[] = [];
  public exerciceSelectionne: Exercice;

  constructor(
    private loginService: LoginService,
    private languageService: JhiLanguageService,
    private sessionStorage: SessionStorageService,
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private profileService: ProfileService,
    private router: Router,
    private agencesService: AgencesService,
    private exercicesService: ExercicesService,
    private refreshService: RefreshService
  ) {
    this.version = VERSION ? (VERSION.toLowerCase().startsWith('v') ? VERSION : 'v' + VERSION) : '';
  }

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      this.inProduction = profileInfo.inProduction;
      this.swaggerEnabled = profileInfo.swaggerEnabled;
    });
    this.agencesService.lister().subscribe((agences: Agence[]) => {
      this.agences = agences;
      if (this.agences.length > 0) {
        const agenceTrouve = this.agences.find(agence => agence.id === +sessionStorage.getItem(ClesSessionStorage.AGENCE));
        if (null != agenceTrouve) {
          this.agenceSelectionnee = agenceTrouve;
        } else {
          sessionStorage.setItem(ClesSessionStorage.AGENCE, String(this.agences[0].id));
          this.agenceSelectionnee = this.agences[0];
        }
      }
    });
    this.exercicesService.lister().subscribe((exercices: Exercice[]) => {
      this.exercices = exercices;
      if (this.exercices.length > 0) {
        const exerciceTrouve = this.exercices.find(exercice => exercice.id === +sessionStorage.getItem(ClesSessionStorage.EXERCICE));
        if (null != exerciceTrouve) {
          this.exerciceSelectionne = exerciceTrouve;
        } else {
          sessionStorage.setItem(ClesSessionStorage.EXERCICE, String(this.exercices[0].id));
          this.exerciceSelectionne = this.exercices[0];
        }
      }
    });
  }

  changeLanguage(languageKey: string): void {
    this.sessionStorage.store('locale', languageKey);
    this.languageService.changeLanguage(languageKey);
  }

  collapseNavbar(): void {
    this.isNavbarCollapsed = true;
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  logout(): void {
    this.collapseNavbar();
    this.loginService.logout();
    this.router.navigate(['']);
  }

  toggleNavbar(): void {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  getImageUrl(): string {
    return this.isAuthenticated() ? this.accountService.getImageUrl() : '';
  }

  changeAgence(): void {
    sessionStorage.setItem(ClesSessionStorage.AGENCE, String(this.agenceSelectionnee.id));
    this.refreshService.refresh();
  }

  changeExercice(): void {
    sessionStorage.setItem(ClesSessionStorage.EXERCICE, String(this.exerciceSelectionne.id));
    this.refreshService.refresh();
  }
}

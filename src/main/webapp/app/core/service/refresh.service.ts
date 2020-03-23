import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class RefreshService {
  constructor(private router: Router) {
  }

  public refresh(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    const currentUrl = this.router.url + '?';
    this.router.navigateByUrl(currentUrl)
    .then(() => {
      this.router.navigated = false;
      this.router.navigate([this.router.url]);
    });
  }
}

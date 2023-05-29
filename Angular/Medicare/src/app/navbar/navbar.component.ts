import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  roleAdmin: any = {};
  currentRoute: string;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private notifyService: NotificationService
  ) {
    router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event) => (this.currentRoute = (event as NavigationEnd).url));
  }

  ngOnInit() {
    this.authService.getType().subscribe((val: any) => {
      this.roleAdmin = val;
    });
  }

  onLogout() {
    this.notifyService.showInfo('You are logged out', 'Session expired');
    this.router.navigate(['/']);
  }
}

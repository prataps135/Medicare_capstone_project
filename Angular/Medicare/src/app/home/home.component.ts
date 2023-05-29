import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../customer';
import { Admin } from '../admin';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { AdminService } from '../admin.service';
import { AuthenticationService } from '../authentication.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  loginForm: FormGroup;
  submitted = false;
  roles = ['Customer', 'Admin'];
  currentUser: Customer;
  adminUser: Admin;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private customerService: CustomerService,
    private adminService: AdminService,
    private authService: AuthenticationService,
    private notifyService: NotificationService
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(15),
        ],
      ],
      login: ['', Validators.required],
    });
  }

  changeRole(e: any) {
    this.loginForm.controls['login'].setValue(e.target.value, {
      onlySelf: true,
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return this.notifyService.showError(
        'All fields are Mandatory',
        'Medicare'
      );
    }
  }

  logIn() {
    const email = this.loginForm.controls['email'].value;
    const password = this.loginForm.controls['password'].value;
    const roles = this.loginForm.controls['login'].value;
    if (roles === this.roles[0]) {
      this.customerService.getByEmail(email).subscribe(
        (data: any) => {
          this.currentUser = data;
          this.authService.setType(this.roles[0]);
          if (this.currentUser.password === password) {
            this.notifyService.showSuccess('Welcome User', 'Medicare');
            this.router.navigate(['/home']);
          } else {
            this.notifyService.showError(
              'Email Id or Password is Incorrect',
              'Please try again'
            );
          }
        },
        (err) => {
          if (this.loginForm.valid) {
            this.notifyService.showError(
              'Email Id or Password is Incorrect',
              'Please try again'
            );
          }
        }
      );
    } else if (roles === this.roles[1]) {
      this.adminService.getByEmailId(email).subscribe(
        (data: any) => {
          this.adminUser = data;
          this.authService.setType(this.roles[1]);
          if (this.adminUser.password === password) {
            this.notifyService.showSuccess('Welcome Admin User', 'Medicare');
            this.router.navigate(['/home']);
          } else {
            this.notifyService.showError(
              'Email Id or Password is Incorrect',
              'Please try again'
            );
          }
        },
        (err: any) => {
          if (this.loginForm.valid) {
            this.notifyService.showError(
              'Email Id or Password is Incorrect',
              'Please try again'
            );
          }
        }
      );
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { AdminRegistrationService } from '../admin-registration.service';
import { Admin } from '../admin';

@Component({
  selector: 'app-admin-registration',
  templateUrl: './admin-registration.component.html',
  styleUrls: ['./admin-registration.component.scss'],
})
export class AdminRegistrationComponent implements OnInit {
  adminRegisterForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private notifyService: NotificationService,
    private router: Router,
    private adminRegistrationService: AdminRegistrationService
  ) {}

  ngOnInit() {
    this.adminRegisterForm = this.formBuilder.group({
      userName: [
        '',
        [
          Validators.required,
          Validators.pattern('[a-zA-Z][a-zA-Z ]+'),
          Validators.minLength(4),
          Validators.maxLength(20),
        ],
      ],
      emailId: ['', Validators.required],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(15),
        ],
      ],
      contactNo: [
        '',
        [Validators.required, Validators.pattern('^((\\+91-?)|0)?[0-9]{10}$')],
      ],
    });
  }

  get f() {
    return this.adminRegisterForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.adminRegisterForm.invalid) {
      return this.notifyService.showError(
        'All fields are Mandatory',
        'Medicare'
      );
    }
  }

  adminRegister() {
    const id = 0;
    const userName = this.adminRegisterForm.controls['userName'].value;
    const emailId = this.adminRegisterForm.controls['emailId'].value;
    const password = this.adminRegisterForm.controls['password'].value;
    const contactNo = this.adminRegisterForm.controls['contactNo'].value;
    const data: Admin = {
      id: id,
      userName: userName,
      emailId: emailId,
      password: password,
      contactNo: contactNo,
    };
    this.adminRegistrationService.addAdmin(data).subscribe(
      (data: any) => {
        this.notifyService.showSuccess('You are Registered', 'Successfully');
        this.router.navigate(['/home']);
      },
      (err) => {
        if (this.adminRegisterForm.valid) {
          this.notifyService.showError(
            'You are already registered',
            'try again'
          );
          this.adminRegisterForm.reset();
        }
      }
    );
  }

  cancel() {
    this.notifyService.showWarn('You are not registered', 'cancelled');
    this.router.navigate(['/home']);
  }
}

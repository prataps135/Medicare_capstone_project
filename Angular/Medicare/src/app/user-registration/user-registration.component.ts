import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotificationService } from '../notification.service';
import { Customer } from '../customer';
import { CustomerRegistrationService } from '../customer-registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss'],
})
export class UserRegistrationComponent implements OnInit {
  customerRegisterForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private notifyService: NotificationService,
    private customerRegistrationService: CustomerRegistrationService,
    private router: Router
  ) {}

  ngOnInit() {
    this.customerRegisterForm = this.formBuilder.group({
      name: [
        '',
        [
          Validators.required,
          Validators.pattern('[a-zA-Z][a-zA-Z ]+'),
          Validators.minLength(4),
          Validators.maxLength(20),
        ],
      ],
      email: ['', Validators.required],
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
    return this.customerRegisterForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.customerRegisterForm.invalid) {
      return this.notifyService.showError(
        'All fields are Mandatory',
        'Medicare'
      );
    }
  }

  register() {
    const id = 0;
    const name = this.customerRegisterForm.controls['name'].value;
    const email = this.customerRegisterForm.controls['email'].value;
    const password = this.customerRegisterForm.controls['password'].value;
    const contactNo = this.customerRegisterForm.controls['contactNo'].value;
    const body: Customer = {
      id: id,
      name: name,
      email: email,
      password: password,
      contactNo: contactNo,
    };
    this.customerRegistrationService.addCustomer(body).subscribe(
      (data: any) => {
        this.notifyService.showSuccess('You are Registered', 'Successfully');
        this.router.navigate(['/']);
      },
      (err) => {
        if (this.customerRegisterForm.valid) {
          this.notifyService.showError('You are not registered', 'try again');
        }
      }
    );
  }

  cancel() {
    this.notifyService.showWarn('You are not registered', 'Cancelled');
    this.router.navigate(['/']);
  }
}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from './customer';

@Injectable({
  providedIn: 'root',
})
export class CustomerRegistrationService {
  constructor(private httpClient: HttpClient) {}

  public addCustomer(data: Customer) {
    const baseUrl = 'http://localhost:8080/customer';
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-originPatterns': '*',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.httpClient.post(baseUrl, data, options);
  }
}

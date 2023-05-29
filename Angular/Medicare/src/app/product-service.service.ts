import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './product';

@Injectable({
  providedIn: 'root',
})
export class ProductServiceService {
  constructor(private http: HttpClient) {}

  public addProduct(data: Product) {
    const baseUrl = 'http://localhost:8080/addproduct';
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-originPatterns': '*',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.http.post(baseUrl, data, options);
  }

  public getById(pid: number) {
    const baseUrl = 'http://localhost:8080/products';
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-originPatterns': '*',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.http.get(baseUrl + '/' + pid, options);
  }

  public updateProduct(pid: number, data: Product) {
    const baseUrl = 'http://localhost:8080/updateproduct';
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-originPatterns': '*',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.http.put(baseUrl + '/' + pid, data, options);
  }

  public deleteProduct(pid: number) {
    const baseUrl = 'http://localhost:8080/deleteproduct';
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-originPatterns': '*',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.http.delete(baseUrl + '/' + pid, options);
  }

  public getAllProducts() {
    const baseUrl = 'http://localhost:8080/getallproducts';
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-originPatterns': '*',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.http.get(baseUrl, options);
  }
}

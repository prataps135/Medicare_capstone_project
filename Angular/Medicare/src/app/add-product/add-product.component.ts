import { Component, OnInit } from '@angular/core';
import { Resource } from '../resource';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductServiceService } from '../product-service.service';
import { Router } from '@angular/router';
import { ProductResourceService } from '../product-resource.service';
import { Product } from '../product';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss'],
})
export class AddProductComponent implements OnInit {
  categories: any;
  resources: Resource[] = [];
  addProductForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductServiceService,
    private router: Router,
    private resourceService: ProductResourceService,
    private notifyService: NotificationService
  ) {}

  ngOnInit() {
    this.addProductForm = this.formBuilder.group({
      pname: ['', [Validators.required, Validators.maxLength(25)]],
      pcat: ['', Validators.required],
      pdesc: ['', [Validators.required, Validators.minLength(10)]],
      price: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
      labelCode: ['', Validators.required],
    });
    this.resourceService.getAllResources().subscribe((response: any) => {
      this.resources = response;
      const categories = this.resources.filter(
        (resources) => resources.resourceCode === 'categories'
      );
      const final = categories.map((categories) => categories.resourceDetail);
      this.categories = final;
    });
  }

  addProduct() {
    if (this.addProductForm.invalid) {
      return this.notifyService.showError(
        'All fields are Mandatory',
        'Medicare'
      );
    }

    const pid = 0;
    const pname = this.addProductForm.controls['pname'].value;
    const pcat = this.addProductForm.controls['pcat'].value;
    const pdesc = this.addProductForm.controls['pdesc'].value;
    const price = this.addProductForm.controls['price'].value;
    const labelCode = this.addProductForm.controls['labelCode'].value;
    const body: Product = {
      pid: pid,
      pname: pname,
      pcat: pcat,
      pdesc: pdesc,
      price: price,
      labelCode: labelCode,
    };
    this.productService.addProduct(body).subscribe(
      (data: any) => {
        this.notifyService.showSuccess(
          'Product Added Successfully',
          'Medicare'
        );
        this.router.navigate(['/home/medicine']);
      },
      (err) => {
        this.notifyService.showSuccess(
          'Product already exist or something went wrong',
          'Try again'
        );
      }
    );
  }

  cancel() {
    this.notifyService.showWarn('Product was not added', 'Cancelled');
    this.router.navigate(['/home']);
  }
}

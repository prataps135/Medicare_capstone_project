import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductServiceService } from '../product-service.service';
import { ProductResourceService } from '../product-resource.service';
import { NotificationService } from '../notification.service';
import { Resource } from '../resource';
import { Product } from '../product';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss'],
})
export class UpdateProductComponent implements OnInit {
  pid: number;
  product: Product;
  categories: any;
  resources: Resource[] = [];
  updateProductForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductServiceService,
    private router: Router,
    private route: ActivatedRoute,
    private resourceService: ProductResourceService,
    private notifyService: NotificationService
  ) {}

  ngOnInit() {
    this.updateProductForm = this.formBuilder.group({
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

    this.route.params.subscribe((params) => (this.pid = +params['pid']));
    this.productService.getById(this.pid).subscribe((resp: any) => {
      this.product = resp;
      this.updateProductForm.controls['pname'].setValue(resp.pid);
      this.updateProductForm.controls['pcat'].setValue(resp.pcat);
      this.updateProductForm.controls['pdesc'].setValue(resp.pdesc);
      this.updateProductForm.controls['price'].setValue(resp.price);
      this.updateProductForm.controls['labelCode'].setValue(resp.labelCode);
    });
  }

  updateProduct() {
    const pid = this.product.pid;
    const pname = this.updateProductForm.controls['pname'].value;
    const pcat = this.updateProductForm.controls['pcat'].value;
    const pdesc = this.updateProductForm.controls['pdesc'].value;
    const price = this.updateProductForm.controls['price'].value;
    const labelCode = this.updateProductForm.controls['labelCode'].value;
    const body: Product = {
      pid: pid,
      pname: pname,
      pcat: pcat,
      pdesc: pdesc,
      price: price,
      labelCode: labelCode,
    };
    this.productService.updateProduct(this.pid, body).subscribe(
      (data: any) => {
        this.notifyService.showSuccess(
          'Product Updated successfully',
          'Medicare'
        );
        this.router.navigate(['/home/medicine']);
      },
      (err) => {
        this.notifyService.showError('Product cannot update', 'Try again');
      }
    );
  }

  cancel() {
    this.notifyService.showWarn('Product was not updated', 'Cancelled');
    this.router.navigate(['home/medicine']);
  }
}

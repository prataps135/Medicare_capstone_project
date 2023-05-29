import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductServiceService } from '../product-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.scss'],
})
export class ViewProductComponent implements OnInit {
  pid: number;
  currentProduct: Product;
  roleAdmin: any = {};
  roleCustomer: any = {};

  constructor(
    private route: ActivatedRoute,
    private productService: ProductServiceService,
    private router: Router,
    private authService: AuthenticationService,
    private notifyService: NotificationService
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => (this.pid = +params['pid']));
    this.productService
      .getById(this.pid)
      .subscribe((res: any) => (this.currentProduct = res));

    this.authService
      .getType()
      .subscribe((role: any) => (this.roleAdmin = role));

    this.authService
      .getType()
      .subscribe((roles: any) => (this.roleCustomer = roles));
  }

  deleteProduct(pid: number) {
    this.productService.deleteProduct(pid).subscribe(
      (res: any) => {
        this.notifyService.showSuccess(
          'Product deleted successfully',
          'Medicare'
        );
        this.router.navigate(['home/medicine']);
      },
      (err) => {
        this.notifyService.showError('Product was not deleted', 'Try again');
      }
    );
  }

  updateProduct(pid: number) {
    this.router.navigate(['home/update-product/' + pid]);
  }

  cancel() {
    this.notifyService.showWarn('going to available products', 'Cancelled');
    this.router.navigate(['home/medicine']);
  }

  purchase() {
    this.router.navigate(['/payment-Summary']);
  }
}

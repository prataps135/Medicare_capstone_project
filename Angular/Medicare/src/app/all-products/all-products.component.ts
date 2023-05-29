import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductServiceService } from '../product-service.service';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.scss'],
})
export class AllProductsComponent implements OnInit {
  products: Product[] = [];
  query: string;
  roleAdmin: any = {};

  constructor(
    private productService: ProductServiceService,
    private router: Router,
    private notifyService: NotificationService,
    private authService: AuthenticationService
  ) {}

  ngOnInit() {
    this.getAllProducts();
    this.authService.getType().subscribe((val: any) => {
      this.roleAdmin = val;
    });
  }

  getAllProducts() {
    this.productService
      .getAllProducts()
      .subscribe((response: any) => (this.products = response));
  }

  viewProduct(pid: number) {
    this.router.navigate(['home/view-product/' + pid]);
  }

  updateProduct(pid: number) {
    this.router.navigate(['home/update-product/' + pid]);
  }

  deleteProduct(pid: number) {
    this.productService.deleteProduct(pid).subscribe(
      (data: any) => {
        this.products = data;
        this.notifyService.showSuccess(
          'Product deleted successfully',
          'Medicare'
        );
      },
      (err) => {
        this.notifyService.showError('Product was not deleted', 'Try again');
      }
    );
  }
}

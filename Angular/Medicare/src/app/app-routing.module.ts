import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { AdminRegistrationComponent } from './admin-registration/admin-registration.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AllProductsComponent } from './all-products/all-products.component';
import { ViewProductComponent } from './view-product/view-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { PaymentSummaryComponent } from './payment-summary/payment-summary.component';
import { SuccessComponent } from './success/success.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'user-registration', component: UserRegistrationComponent },
  { path: 'payment-Summary', component: PaymentSummaryComponent },
  { path: 'Success', component: SuccessComponent },
  {
    path: 'home',
    component: NavbarComponent,
    children: [
      { path: 'medicine', component: AllProductsComponent },
      { path: 'add-medicine', component: AddProductComponent },
      { path: 'view-product/:pid', component: ViewProductComponent },
      { path: 'update-product/:pid', component: UpdateProductComponent },
      { path: 'admin-registration', component: AdminRegistrationComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

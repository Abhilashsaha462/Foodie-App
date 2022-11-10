import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmindashboardComponent } from './Admin/admindashboard/admindashboard.component';
import { AdminloginComponent } from './Admin/adminlogin/adminlogin.component';
import { ContainerComponent } from './container/container.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { AdminGuard } from './Guards/admin.guard';
import { LoginGuard } from './Guards/login.guard';
import { OwnerLoginGuard } from './Guards/owner-login.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { OwnerLoginComponent } from './Owner/owner-login/owner-login.component';
import { OwnerRegisterComponent } from './Owner/owner-register/owner-register.component';
import { OwnerdataComponent } from './Owner/ownerdata/ownerdata.component';
import { RegisterComponent } from './register/register.component';
import { DisplayRestaurantComponent } from './Restaurants/display-restaurant/display-restaurant.component';
import { RestaurantDetailsComponent } from './Restaurants/restaurant-details/restaurant-details.component';
import { RestaurantComponent } from './Restaurants/restaurant/restaurant.component';

const routes: Routes = [
  {
    path : '',
    component : HomeComponent
  },
  {
    path : 'home',
    component : HomeComponent
  },
  {
    path : 'login',
    component : LoginComponent
  },
  {
    path : 'register',
    component : RegisterComponent
  },
  {
    path : 'editrestaurant',
    component : RestaurantComponent,
    pathMatch:'full',
    canActivate: [LoginGuard]
  },
  {
    path : 'restaurants-details',
    component : RestaurantDetailsComponent,
    canActivate: [LoginGuard]
  },
  {
    path : 'favorites',
    component : FavoriteComponent,
    pathMatch: 'full',
    canActivate: [LoginGuard]
  },
  {
    path: 'display',
    component : DisplayRestaurantComponent,
    pathMatch:'full'
  },
  {
    path: 'adminLog',
    component:AdminloginComponent,
    pathMatch:'full'
  },
  {
    path: 'admindash',
    component: AdmindashboardComponent,
    pathMatch:'full',
    canActivate:[AdminGuard]
  },
  {
    path: 'forgot',
    component: ForgotPasswordComponent,
    pathMatch:'full'
  },
  {
    path: 'ownerdash',
    component:OwnerdataComponent,
    pathMatch:'full',
    canActivate:[OwnerLoginGuard]
  },
  {
    path: 'ownerregister',
    component:OwnerRegisterComponent,
    pathMatch:'full'
  },
  {
    path : 'register',
    component : RegisterComponent
  },
  {
    path : 'login',
    component : LoginComponent
  },
  {
    path:'ownerlogin',
    component:OwnerLoginComponent,
    pathMatch:'full'
  },
  {
    path: 'order',
    component:OrderComponent
  },
  {
    path : 'container',
    component : ContainerComponent,
    children : [
      {
        path : '',
        component : LoginComponent
      },
      
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

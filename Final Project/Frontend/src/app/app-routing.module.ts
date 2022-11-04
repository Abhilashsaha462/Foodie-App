import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmindashboardComponent } from './Admin/admindashboard/admindashboard.component';
import { AdminloginComponent } from './Admin/adminlogin/adminlogin.component';
import { ContainerComponent } from './container/container.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { AdminGuard } from './Guards/admin.guard';
import { LoginGuard } from './Guards/login.guard';
import { OwnerLoginGuard } from './Guards/owner-login.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OwnerContainerComponent } from './Owner/owner-container/owner-container.component';
import { OwnerDataComponent } from './Owner/owner-data/owner-data.component';
import { OwnerLoginComponent } from './Owner/owner-login/owner-login.component';
import { OwnerRegisterComponent } from './Owner/owner-register/owner-register.component';
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
    pathMatch:'full'
  },
  {
    path : 'restaurants-details',
    component : RestaurantDetailsComponent
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
    path: 'ownerdash',
    component:OwnerDataComponent,
    pathMatch:'full',
    canActivate:[OwnerLoginGuard]
  },
  {
    path: 'ownerregister',
    component:OwnerRegisterComponent,
    pathMatch:'full'
  },
  {
    path:'ownerlogin',
    component:OwnerLoginComponent,
    pathMatch:'full'
  },
  {
    path : 'container',
    component : ContainerComponent,
    children : [
      {
        path : '',
        component : LoginComponent
      },
      {
        path : 'login',
        component : LoginComponent
      },
      {
        path : 'register',
        component : RegisterComponent
      }
    ]
  },
  {
    path : 'owner-container',
    component : OwnerContainerComponent,
    children : [
      {
        path : '',
        component : OwnerLoginComponent
      },
      {
        path : 'owner-login',
        component : OwnerLoginComponent
      },
      {
        path : 'owner-register',
        component : OwnerRegisterComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

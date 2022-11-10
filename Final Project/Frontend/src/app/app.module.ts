import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeadercompComponent } from './headercomp/headercomp.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatInputModule} from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { ImageComponent } from './image/image.component';
import { RestaurantComponent } from './Restaurants/restaurant/restaurant.component';
import { CommonModule }   from '@angular/common';
import { HomeComponent } from './home/home.component';
import { RestaurantDetailsComponent } from './Restaurants/restaurant-details/restaurant-details.component';
import { ContainerComponent } from './container/container.component';
import {MatDialogModule} from '@angular/material/dialog';
import { OwnerProfileComponent } from './owner-profile/owner-profile.component';
import { RestaurantEditComponent } from './Restaurants/restaurant-add/restaurant-edit.component';
import {MatTableModule} from '@angular/material/table';
import { DisplayRestaurantComponent } from './Restaurants/display-restaurant/display-restaurant.component'
import {MatCheckboxModule} from '@angular/material/checkbox';
// import { FlexLayoutModule } from '@angular/flex-layout';
import { OrderComponent } from './order/order.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { AdminloginComponent } from './Admin/adminlogin/adminlogin.component';
import { AdmindashboardComponent } from './Admin/admindashboard/admindashboard.component';
import {MatTabsModule} from '@angular/material/tabs';
import { OwnerdataComponent } from './Owner/ownerdata/ownerdata.component';
import { OwnerLoginComponent } from './Owner/owner-login/owner-login.component';
import { OwnerRegisterComponent } from './Owner/owner-register/owner-register.component';
import { EditRestaurantComponent } from './Owner/edit-restaurant/edit-restaurant.component';
import * as $ from 'jquery';
import Swal from 'sweetalert2';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
@NgModule({
  declarations: [
    AppComponent,
    HeadercompComponent,
    RegisterComponent,
    LoginComponent,
    ImageComponent,
    RestaurantComponent,
    HomeComponent,
    RestaurantDetailsComponent,
    ContainerComponent,
    OwnerProfileComponent,
    RestaurantEditComponent,
    DisplayRestaurantComponent,
    OrderComponent,
    FavoriteComponent,
    AdminloginComponent,
    AdmindashboardComponent,
    OwnerdataComponent,
    OwnerLoginComponent,
    OwnerRegisterComponent,
    EditRestaurantComponent,
    ForgotPasswordComponent,
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    LayoutModule,HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,MatTabsModule,MatPaginatorModule,
    MatIconModule,MatTooltipModule,MatInputModule,HttpClientModule,MatDialogModule,MatTableModule,MatCheckboxModule,
    // FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

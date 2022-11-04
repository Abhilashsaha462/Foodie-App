import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
import { HttpClientModule } from '@angular/common/http';
import { CommonModule }   from '@angular/common';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
// import { FlexLayoutModule } from '@angular/flex-layout';
import {MatTabsModule} from '@angular/material/tabs';
import * as $ from 'jquery';
// import Swal from 'sweetalert2';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ContainerComponent } from './container/container.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { HeadercompComponent } from './headercomp/headercomp.component';
import { HomeComponent } from './home/home.component';
import { ImageComponent } from './image/image.component';
import { OrderComponent } from './order/order.component';
import { OwnerProfileComponent } from './owner-profile/owner-profile.component';
import { AdmindashboardComponent } from './Admin/admindashboard/admindashboard.component';
import { AdminloginComponent } from './Admin/adminlogin/adminlogin.component';
import { EditRestaurantComponent } from './Owner/edit-restaurant/edit-restaurant.component';
import { OwnerLoginComponent } from './Owner/owner-login/owner-login.component';
import { OwnerRegisterComponent } from './Owner/owner-register/owner-register.component';
import { OwnerDataComponent } from './Owner/owner-data/owner-data.component';
import { DisplayRestaurantComponent } from './Restaurants/display-restaurant/display-restaurant.component';
import { RestaurantComponent } from './Restaurants/restaurant/restaurant.component';
import { RestaurantAddComponent } from './Restaurants/restaurant-add/restaurant-add.component';
import { RestaurantDetailsComponent } from './Restaurants/restaurant-details/restaurant-details.component';
import { OwnerContainerComponent } from './Owner/owner-container/owner-container.component';

@NgModule({
  declarations: [
    AppComponent,
    ContainerComponent,
    LoginComponent,
    RegisterComponent,
    FavoriteComponent,
    HeadercompComponent,
    HomeComponent,
    ImageComponent,
    OrderComponent,
    OwnerProfileComponent,
    AdmindashboardComponent,
    AdminloginComponent,
    EditRestaurantComponent,
    OwnerLoginComponent,
    OwnerRegisterComponent,
    OwnerDataComponent,
    DisplayRestaurantComponent,
    RestaurantComponent,
    RestaurantAddComponent,
    RestaurantDetailsComponent,
    OwnerContainerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    LayoutModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatTabsModule,
    MatPaginatorModule,
    MatIconModule,
    MatTooltipModule,
    MatInputModule,
    HttpClientModule,
    MatDialogModule,
    MatTableModule,
    MatCheckboxModule,
    // FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

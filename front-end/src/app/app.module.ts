
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';
import { httpInterceptorProviders } from './http-interceptors/';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BooksComponent } from './Administrador/books/books.component';
import { OrdersComponent } from './Administrador/orders/orders.component';
import { BookComponent } from './Cliente/book/book.component';
import { OrderComponent } from './Cliente/order/order.component';

import { LoginComponent } from '../app/account/login/login.component';
import { CreateAccountComponent } from '../app/account/create-account/create-account.component';

import { HomeComponent } from '../app/layout/home/home.component';
import { AuthenticationComponent } from '../app/layout/authentication/authentication.component';
import { FormBookComponent } from './Administrador/form-book/form-book.component';

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      /*{ path: 'register', component: RegisterComponent },
      { path: 'adm/books', component: BooksComponent },
      { path: 'adm/orders', component: OrdersComponent },
      { path: 'books', component: BookComponent },
      { path: 'orders', component: OrderComponent },*/
      {
        path: '',
        component: HomeComponent,
        children: [
          { path: '', redirectTo: 'login', pathMatch: 'full' },
          { path: 'book', component: BookComponent },
          { path: 'books', component: BooksComponent },
          { path: 'form-book', component: FormBookComponent },
          { path: 'orders', component: OrdersComponent },
          { path: 'order', component: OrderComponent },
          { path: 'login', component: LoginComponent },
          { path: 'create-account', component: CreateAccountComponent }
        ],
        //canActivate: [AuthGuard]
      },
      {
        path: '',
        component: AuthenticationComponent,
        children: [
          { path: '', redirectTo: 'login', pathMatch: 'full' },
          { path: 'login', component: LoginComponent },
          { path: 'create-account', component: CreateAccountComponent }
        ]
      }
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    BooksComponent,
    OrdersComponent,
    BookComponent,
    OrderComponent,
    LoginComponent,
    CreateAccountComponent,
    HomeComponent,
    AuthenticationComponent,
    FormBookComponent
   ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})

export class AppModule { }

import { Order } from './../../Cliente/order/interface/Order';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AdminService } from '../AdminService.service';
import { Admin } from '../Admin';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders : Order[]
  user: Admin;

  private url: string;

  constructor(private http: HttpClient, private adminService: AdminService, private router: Router) {
    this.url = 'http://localhost:8080/orders';
    this.user = adminService.user
  }

  ngOnInit() {
    if(this.user){
      this.getOrders();
    }else{
      this.router.navigate(['login'])
    }
  }

  getOrders(): Observable<Order[]>{
    return this.http.get<Order[]>(this.url).subscribe(orders => this.orders = orders);
  }

  aprovar(order: Order){
    order.orderStatus = "APPROVED";
    return this.http.put<Order>(this.url + '/approve/' + order.id, order).subscribe();
  }

  recusar(order: Order){
    order.orderStatus = "REFUSED";
    return this.http.put<Order>(this.url + '/refuse/' + order.id, order).subscribe();
  }

}

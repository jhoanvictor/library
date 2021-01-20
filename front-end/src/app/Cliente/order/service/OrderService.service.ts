import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Order } from '../interface/Order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/orders/';
  }

  public findAll(): Observable<Order[]> {
    return this.http.get<Order[]>(this.url);
  }

  public save(order: Order) {
    console.log(order)
    return this.http.post<Order>(this.url, order).subscribe();
  }

}

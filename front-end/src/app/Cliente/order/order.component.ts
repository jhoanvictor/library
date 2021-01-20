import { Client } from './../Client';
import { OrderService } from './service/OrderService.service';
import { Book } from './../book/interface/Book';

import { Component, OnInit } from '@angular/core';
import { BookService } from '../book/service/BookService.service';
import { Order } from './interface/Order';
import { UserService } from '../UserService.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  books: Book[];
  order: Order;
  client: Client;

  constructor(private bookService: BookService,
    private orderService: OrderService,
    private userService: UserService,
    private router: Router) {

    this.client = new Client();
    this.order = new Order();
   }

  ngOnInit() {
    if(this.userService.user){
      this.getBooks()
    }else{
      this.router.navigate(['login'])
    }
  }

  /** Busca livros disponíveis */
  getBooks(){
    this.bookService.getBooks().subscribe(books => this.books = books);
  }

  alugar(book : Book){
    this.client.id = this.userService.user.id;
    this.order.book = book;
    this.order.client = this.client;
    this.orderService.save(this.order);
  }

}

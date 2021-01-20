import { Client } from './../Client';
import { UserService } from './../UserService.service';
import { Component, OnInit } from '@angular/core';
import { BookService } from './service/BookService.service';
import { Book } from './interface/Book';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-books',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  name: string;
  books: Book[];
  user: Client;

  constructor(
    private bookService: BookService,
    private http: HttpClient,
    private userService: UserService,
    private router: Router) {
    this.user = this.userService.user
  }

  ngOnInit() {
    if(this.user){
      this.getBooks()
    }else{
      this.router.navigate(['login'])
    }
  }

  getBooks(){
      this.bookService.getBooksRented(this.user).subscribe(books => this.books = books);
  }

  devolution(book: Book){
    this.bookService.devolution(book)
  }

}

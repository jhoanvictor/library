import { AdminService } from './../AdminService.service';
import { Book } from './../../Cliente/book/interface/Book';
import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/Cliente/book/service/BookService.service';
import { Router } from '@angular/router';
import { Admin } from '../Admin';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit{

  books: Book[];
  user: Admin;

  constructor(private bookService: BookService, private adminService: AdminService, private router: Router) {
    this.user = adminService.user
  }

  ngOnInit() {

    if(this.user){
      this.listBooks()
    }else{
      this.router.navigate(['login'])
    }

  }

  removeToBook(book: Book){
    this.bookService.delete(book);
    window.alert("Livro Deletado: " + book.name)
  }

  updateToBook(book: Book){
    alert('Função não implementada')
    //this.bookService.update(book).subscribe();
  }

  listBooks(){
    this.bookService.findAll().subscribe(books => this.books = books);
  }

}

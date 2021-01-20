import { Client } from './../../Client';
import { Book } from './../interface/Book';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/books';
  }

  findAll() : Observable<Book[]> {
    return this.http.get<Book[]>(this.url);
  }

  save(book: Book) {
    return this.http.post<Book>(this.url, book).subscribe();
  }

  update(book: Book){
    return this.http.put<Book>(this.url, book);
  }

  delete(book: Book){
    return this.http.delete<Book>(this.url + '/' + book.id).subscribe();
  }

  /** Livros Disponíveis para alugar */
  getBooks(): Observable<Book[]>{
    return  this.http.get<Book[]>(this.url + '/rent');
  }

  getBooksRented(user: Client): Observable<Book[]>{
    return  this.http.get<Book[]>(this.url + '/rented/' + user.id);
  }

  devolution(book : Book){
    return this.http.put<Book>(this.url + '/devolution/' + book.id, book).subscribe();
  }

}

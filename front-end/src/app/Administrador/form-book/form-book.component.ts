import { Book } from './../../Cliente/book/interface/Book';
import { BookService } from 'src/app/Cliente/book/service/BookService.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../AdminService.service';
import { Admin } from '../Admin';

@Component({
  selector: 'app-form-book',
  templateUrl: './form-book.component.html',
  styleUrls: ['./form-book.component.scss']
})
export class FormBookComponent implements OnInit {

  book = new Book()
  user: Admin;

  constructor(
    private router: Router,
    private BookService: BookService,
    private adminService: AdminService) {
      this.user = adminService.user
    }

  ngOnInit() {
    if(!this.user){
      this.router.navigate(['login'])
    }
  }

  saveBook(){
    try{
      this.BookService.save(this.book);
      window.alert('Cadastro Efetuado');
    }catch(error){
      console.log(error)
    }
  }



}

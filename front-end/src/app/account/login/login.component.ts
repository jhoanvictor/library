import { AdminService } from './../../Administrador/AdminService.service';
import { UserService } from './../../Cliente/UserService.service';
import { Client } from './../../Cliente/Client';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AccountService } from './../shared/account.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from 'src/app/Administrador/Admin';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  model: any = {}
  user: Client;
  admin: Admin;

  constructor(
    private accountService: AccountService,
    private router: Router,
    private http: HttpClient,
    private userService: UserService,
    private adminService: AdminService
  ) { }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }

  async login() {
    let url = 'http://localhost:8080/clients/login';
    let urlAdmin = 'http://localhost:8080/admin/login';

    const result = await this.http.post<Client>(url, {
      email: this.model.email,
      password: this.model.password
    }).toPromise();

    const resultAdmin = await this.http.post<Admin>(urlAdmin, {
      email: this.model.email,
      password: this.model.password
    }).toPromise();

    if(result){
        this.user = result
        this.userService.user = this.user
        this.router.navigate(['book'])
    }

    if(resultAdmin){
        this.admin = resultAdmin
        this.adminService.user = this.admin
        this.router.navigate(['books'])
    }

    if(!result && !resultAdmin){
      alert("Authentication failed.")
    }

  }


}



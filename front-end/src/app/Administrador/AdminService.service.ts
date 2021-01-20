import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Admin } from './Admin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private url: string;

  user: Admin

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/admin';
  }

  public findAll(): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.url);
  }

  public save(user: Admin) {
    return this.http.post<Admin>(this.url, user);
  }

}

import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Client } from './Client';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string;

  user: Client

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/clients';
  }

  public findAll(): Observable<Client[]> {
    return this.http.get<Client[]>(this.url);
  }

  public save(user: Client) {
    return this.http.post<Client>(this.url, user);
  }

}

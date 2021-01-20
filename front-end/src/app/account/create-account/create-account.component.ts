import { AccountService } from './../shared/account.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent implements OnInit {

  account = {
    name: '',
    email: '',
    password: ''
  };

  constructor(private accountService: AccountService, private router: Router) { }

  ngOnInit() {
  }

  async onSubmit() {
    try {
      const result = await this.accountService.createAccount(this.account);

      if(result && result.id){
        alert('Conta criada com sucesso. Bem vindo!')
        this.router.navigate(['']);
      }

      // exibir uma msg amigavel aqui

    } catch (error) {
      console.error(error);
    }
  }
}

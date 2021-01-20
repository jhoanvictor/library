import { Book } from '../../book/interface/Book';
import { Client } from '../../Client';

export class Order {
  id: number;
  orderStatus: string;
  client :  Client;
  book :  Book
}

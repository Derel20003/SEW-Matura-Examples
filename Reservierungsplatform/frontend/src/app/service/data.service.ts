import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  custId?: string = "-1";

  constructor() { }
}

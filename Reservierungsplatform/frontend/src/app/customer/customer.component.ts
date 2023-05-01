import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpService} from "../service/http.service";
import {DataService} from "../service/data.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Customer} from "../model/customer.model";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  customer?: Customer

  customerForm = this.fb.group({
    first_name: [null, Validators.required],
    last_name: [null, Validators.required],
    street: [null, Validators.required],
    house_number: null,
    city: [null, Validators.required],
    zip_code: [null, Validators.compose([
      Validators.required, Validators.minLength(4), Validators.maxLength(5)
    ])],
    password: [null, Validators.compose([
      Validators.required, Validators.minLength(6)
    ])],
    birth_date: [null],
    email: [null],
    tel_number: [null]
  });

  hasUnitNumber = false;

  constructor(private fb: FormBuilder,
              private httpService: HttpService,
              private dataService: DataService,
              private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.dataService.custId="-1"
    this.httpService.getCustomer().subscribe(
      (c: Customer) => {
        this.customerForm.patchValue(c)
      }
    )
  }

  onSubmit(): void {
    this.customer = Object.assign({}, this.customerForm.value)
    this.httpService.updateCustomer(this.customer!).subscribe({next: () => {
        this.snackBar.open('Customer Daten erfoglreich gespeichert!', '', { duration: 3000, panelClass: "snackbar-dark"});
      }, error: error => {
        this.snackBar.open('Fehler beim Speichern! (' + error.message + ')', '', { duration: 3000, panelClass: "snackbar-dark"});
      }});
  }
}

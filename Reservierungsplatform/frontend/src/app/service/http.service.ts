import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataService} from "./data.service";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";
import {Reservation} from "../model/reservation.model";
import {CourtType} from "../model/courttype.model";
import {Court} from "../model/court.model";
import {Times} from "../model/times.model";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  SERVERURL = 'http://localhost:8080/api/'

  constructor(private http: HttpClient,
              private data: DataService) { }

  createReservation(reservation: Reservation): Observable<any> {
    return this.http.post<Reservation>(this.SERVERURL + 'reservation', reservation)
  }

  getAvailableTimes(duration: number, date: string, sport: string): Observable<Times[]> {
    return this.http.get<Times[]>(
      this.SERVERURL + 'reservation/available-times?duration=' + duration + '&date=' + date + '&sport=' + sport
    )
  }

  getCourtsByType(type: string): Observable<Court[]> {
    return this.http.get<Court[]>(
      this.SERVERURL + 'court/courts-by-type/?description=' + type
    )
  }

  getCourtTypes(): Observable<CourtType[]> {
    return this.http.get<CourtType[]>(this.SERVERURL + 'courttype/all')
  }

  getCustomer(): Observable<Customer> {
    return this.http.get<Customer>(this.SERVERURL + 'customer/' + this.data.custId)
  }

  updateCustomer(cust: Customer): Observable<Customer> {
    return this.http.put<Customer>(this.SERVERURL + 'customer/' + this.data.custId, cust)
  }

  getReservationList(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(
      this.SERVERURL + 'reservation/all-for-user/' + this.data.custId
    )
  }

  deleteReservation(id: number): Observable<any> {
    return this.http.delete(this.SERVERURL + 'reservation/' + id)
  }
}

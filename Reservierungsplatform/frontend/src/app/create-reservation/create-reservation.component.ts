import {AfterViewInit, Component} from '@angular/core';
import {Reservation} from "../model/reservation.model";
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {HttpService} from "../service/http.service";
import {DataService} from "../service/data.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CourtType} from "../model/courttype.model";
import {Court} from "../model/court.model";
import {Times} from "../model/times.model";

@Component({
  selector: 'app-create-reservation',
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css']
})
export class CreateReservationComponent implements AfterViewInit {
  reservation?: Reservation
  courtTypes?: CourtType[]
  courts?: Court[]
  times: Times[] = []

  displayedColumns = ['time'];

  reservationForm = this.fb.group({
    date: [new Date(), Validators.compose([
      Validators.required, this.ValidateDateAfterToday
    ])],
    sport: [{disable: this.shouldDisableCourts()}, Validators.required],
    duration: [60, Validators.compose([
      Validators.required, this.ValidateModulo
    ])]
  })

  constructor(private fb: FormBuilder,
              private httpService: HttpService,
              private dataService: DataService,
              private snackBar: MatSnackBar) { }

  ngAfterViewInit(): void {
    this.getAvailableTimes()
    this.httpService.getCourtTypes().subscribe((courtTypes: CourtType[]) => {
      this.courtTypes = []
      courtTypes.forEach(courtType => {
        const newCourtType: CourtType = { name: courtType.toString() }
        this.courtTypes?.push(newCourtType)
      })
    })
  }

  createReservation(start_time: Date, end_time: Date, court_id: number) {
    this.reservation = {
      customer_id: Number(this.dataService.custId),
      start_time: start_time,
      end_time: end_time,
      timestamp: new Date(),
      court_id: court_id
    }
    this.httpService.createReservation(this.reservation).subscribe({next: () => {
        this.snackBar.open('Reservierung erfoglreich gespeichert!', '', { duration: 3000, panelClass: "snackbar-dark"});
      }, error: error => {
        this.snackBar.open('Fehler beim Speichern! (' + error.message + ')', '', { duration: 3000, panelClass: "snackbar-dark"});
      }});
  }

  ValidateModulo(control: AbstractControl): {[key: string]: any} | null  {
    if (control.value && control.value % 30 != 0) {
      return { 'stepInvalid': true };
    }
    return null;
  }

  ValidateDateAfterToday(control: AbstractControl): {[key: string]: any} | null  {
    let today = new Date().setHours(0, 0, 0, 0)
    if (control.value && today > control.value) {
      return { 'dateInvalid': true };
    }
    return null;
  }

  shouldDisableCourts(): boolean {
    return !this.courts
  }

  getAvailableTimes(): void {
    this.httpService.getAvailableTimes(
      this.reservationForm.value.duration,
      new Date(this.reservationForm.value.date).toLocaleDateString(),
      this.reservationForm.value.sport).subscribe(times => {
      this.times = times
    })
  }

  getCourtsByType(type: string): void {
    this.httpService.getCourtsByType(type).subscribe((courts: Court[]) => {
      this.courts = []
      courts.forEach((court: Court) => {
        const newCourt: Court = {
          id: Number(court[0 as unknown as keyof Court]),
          name: court[1 as unknown as keyof Court].toString(),
          sport: court[2 as unknown as keyof Court].toString()
        }
        this.courts?.push(newCourt)
      })
    })
  }

  onSubmit() {

  }

}

import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {Reservation} from "../model/reservation.model";
import {HttpService} from "../service/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Reservation>;
  dataSource: MatTableDataSource<Reservation>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'start_time', 'end_time', 'timestamp', 'action']

  constructor(
    private httpService: HttpService,
    private snackBar: MatSnackBar
  ) {
    this.dataSource = new MatTableDataSource<Reservation>();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
    this.refreshData();
  }

  refreshData(): void {
    this.httpService.getReservationList().subscribe((resList) => {
      this.dataSource.data = resList;
    })
  }

  allowStorno(res: Reservation): boolean {
    return (new Date(res.start_time) > new Date());
  }

  delete(id: number): void {
    this.httpService.deleteReservation(id).subscribe({
      complete: () => {
        this.snackBar.open('Reservierung wurde storniert!', '',
          {duration: 3000, panelClass: 'snackbar-dark'})
        this.refreshData()
      },
      error: error => {
        this.snackBar.open('Fehler beim Stornierversuch! (' + error.message + ')', '',
          {duration: 3000, panelClass: 'snackbar-dark'})
      }
    })
  }
}

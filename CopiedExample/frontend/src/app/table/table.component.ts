import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {TableDataSource} from './table-datasource';
import {HttpService} from "../service/http.service";
import {UserModel} from "../model/user.model";

const USER: UserModel[] = []

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  dataSource = new MatTableDataSource<UserModel>();
  user: UserModel[] = []
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['firstname', 'lastname'];

  constructor(private http: HttpService) {

  }

  ngAfterViewInit(): void {
    this.http.getUser().subscribe((c => {
      this.user = c;
      this.dataSource.data = this.user
    }))
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

    console.log(this.user)
  }
}

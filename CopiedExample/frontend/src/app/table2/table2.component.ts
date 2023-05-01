import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {Table2DataSource} from './table2-datasource';
import {UserModel} from "../model/user.model";
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-table2',
  templateUrl: './table2.component.html',
  styleUrls: ['./table2.component.css']
})
export class Table2Component implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  dataSource = new MatTableDataSource<UserModel>()
  user: UserModel[] = []
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['firstname', 'lastname'];

  constructor(private http: HttpService) {
  }

  ngAfterViewInit(): void {
    this.http.getUser().subscribe(c=>{
      this.dataSource.data = c
    })
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

  }
}

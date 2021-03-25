import { Component, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { CloseTableModalComponent } from './pedido/close-table/close-table-modal.component';
import { MakeOrderModalComponent } from './pedido/make-order/make-order-modal.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'restaurante-app';
  @ViewChild('sidenav') sidenav: MatSidenav;

  constructor(private matDialog: MatDialog) {

  }

  openPedidoDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "40%"
    dialogConfig.height = "auto"
    dialogConfig.id = 'make-order-dialog'
    this.matDialog.open(MakeOrderModalComponent, dialogConfig)
  }

  openFecharPedidoDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "40%"
    dialogConfig.height = "auto"
    dialogConfig.id = 'make-order-dialog'
    this.matDialog.open(CloseTableModalComponent, dialogConfig)
  }
}

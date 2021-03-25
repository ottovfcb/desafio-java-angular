import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-close-table-modal',
  templateUrl: './close-table-modal.component.html',
  styleUrls: ['./close-table-modal.component.scss']
})
export class CloseTableModalComponent implements OnInit {

  mesaNumber: any;
  tableClosed: boolean = false;
  mesasList: any = [];
  data: any;
  aviso: any;

  constructor(private dialogRef: MatDialogRef<CloseTableModalComponent>,
    private pedidoService : PedidoService) { }

  ngOnInit(): void {

    this.pedidoService.listTables().subscribe(result => {
      this.mesasList = result
    })

  }

  fecharPedidos(){
    this.pedidoService.closeTableOrders(this.mesaNumber).subscribe(result => {
      this.data = result
      if (this.data.valorTotal == 0) {
        this.aviso = "Não há pedidos concluídos na mesa " + this.mesaNumber
      } else {
        this.aviso = "Pedidos concluídos da mesa " + this.mesaNumber + " foram fechados!"
      }
      this.tableClosed = true;
    })
  }

  closeModal(){
    this.dialogRef.close();
}

}

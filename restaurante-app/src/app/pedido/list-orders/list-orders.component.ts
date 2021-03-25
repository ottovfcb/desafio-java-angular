import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { OrderModel } from 'src/app/models/order.model';
import { PedidoService } from 'src/app/services/pedido.service';
import { MakeOrderModalComponent } from '../make-order/make-order-modal.component';

@Component({
  selector: 'app-list-orders',
  templateUrl: './list-orders.component.html',
  styleUrls: ['./list-orders.component.scss']
})
export class ListOrdersComponent implements OnInit {

  datasource: any = [];
  dataSourceHeaders: string[] = ['description', 'value', 'table', 'status', 'actions']
  filtroAtivo: boolean = false

  constructor(private pedidoService: PedidoService, private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.listarPedidos()
  }
  
  listarPedidos(): void {
    this.pedidoService.listAll().subscribe( result => {
      this.datasource = new MatTableDataSource(result)
    })
  }

  edit(order){
    var copy = this.clone(order)
    const modalData = new OrderModel(copy.item, copy.valor, copy.mesa, copy.id)

    this.matDialog.open(MakeOrderModalComponent, {
      width: "40%",
      height: "60%",
      id: 'make-order-dialog',
      data: modalData
    })
  }

  concluirPedido(order){
    let id = order.id
  
    this.pedidoService.concluirPedido(id).subscribe(() => {
    }).add(() =>{
      window.location.reload()
    })
  }

  cancelarPedido(order){
    let id = order.id
  
    this.pedidoService.cancelarPedido(id).subscribe(() => {
    }).add(() =>{
      window.location.reload()
    })
  }

  filterPendings(checked){
    if (checked) {
      this.pedidoService.listarPendentes().subscribe(result => {
        this.datasource = new MatTableDataSource(result)
      })
    }
    else {
      this.listarPedidos()
    }
  }

  clone(obj) { 
    var copy;

    if (null == obj || "object" != typeof obj) return obj;

    if (obj instanceof Date) {
      copy = new Date();
      copy.setTime(obj.getTime());
      return copy;
    }

    if (obj instanceof Array) {
      copy = [];
      for (var i = 0, len = obj.length; i < len; i++) {
        copy[i] = this.clone(obj[i]);
      }
      return copy;
    }

    if (obj instanceof Object) {
      copy = {};
      for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = this.clone(obj[attr]);
      }
      return copy;
    }

  }

}

import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators, FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { OrderModel } from 'src/app/models/order.model';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-make-order-modal-component',
  templateUrl: './make-order-modal.component.html',
  styleUrls: ['./make-order-modal.component.scss']
})
export class MakeOrderModalComponent implements OnInit {

  buttonLabel: String = "Fazer Pedido";

  item: string = null;
  valor: any = null;
  mesa: any = null;

  id: any = null;
 
  constructor(private dialogRef: MatDialogRef<MakeOrderModalComponent>,
    private pedidoService : PedidoService,
    @Inject(MAT_DIALOG_DATA) public dialogData: OrderModel) { 
      if(dialogData != null){
        this.item = dialogData.item;
        this.valor = dialogData.valor
        this.mesa = dialogData.mesa
        this.id = dialogData.id
        this.buttonLabel = "Editar Pedido"
      }
    }

  ngOnInit(): void {
  }

  closeModal(){
      this.dialogRef.close();
  }

  makeOrder(){
    if(this.id != null){
      const order = new OrderModel(this.item, this.valor, this.mesa, this.id);
      this.pedidoService.editOrder(order).subscribe(() => {
      }).add(() =>{
        this.closeModal()
        window.location.reload()
      })
    }else{
      this.pedidoService.saveOrder(this.item, this.valor, this.mesa).subscribe( () => {
      }).add(() => {
        this.closeModal()
        window.location.reload()
      });
    }
  }
}

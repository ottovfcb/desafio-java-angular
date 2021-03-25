import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PedidoComponent} from "./pedido/pedido.component"
import { ListOrdersComponent } from "./pedido/list-orders/list-orders.component"

const routes: Routes = [
  {
    path: '',
    redirectTo: '/pedido',
    pathMatch: 'full'
  },
  {
    path:'pedido',
    component: PedidoComponent
  },
  {
    path:'pedido/detalharTodos',
    component: ListOrdersComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

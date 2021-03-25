import { HttpClient, HttpParams } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })

export class PedidoService {

    

    constructor(private http: HttpClient){
    }

    saveOrder(item, valor, mesa){

        let params = new HttpParams()
         .set('item', item)
         .set('valor', valor)
         .set('mesa', mesa)

        return this.http.post<any[]>('http://localhost:8080/api/v1/pedidos/', {item: item, valor: valor, mesa: mesa})
    }

    listAll(){
        return this.http.get<any[]>('http://localhost:8080/api/v1/pedidos')
    }

    editOrder(order){
        return this.http.put<any[]>('http://localhost:8080/api/v1/pedidos/alterarPedido', order)
    }

    cancelarPedido(id){
        let url = `http://localhost:8080/api/v1/pedidos/cancelarPedido/${id}`;
        return this.http.put<any[]>(url, {})
    }

    concluirPedido(id){
        let url = `http://localhost:8080/api/v1/pedidos/concluirPedido/${id}`;
        return this.http.put<any[]>(url, {})
    }

    listTables(){
        return this.http.get<any[]>('http://localhost:8080/api/v1/pedidos/listTables')
    }

    closeTableOrders(mesa){ 
        let url = `http://localhost:8080/api/v1/pedidos/fecharConta/${mesa}`;
        return this.http.put<any[]>(url, {})
    }

    listarPendentes(){
        let url = `http://localhost:8080/api/v1/pedidos/pendentes`
        return this.http.get<any[]>(url, {})
    }

}
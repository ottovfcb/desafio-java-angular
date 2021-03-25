import { Pipe, PipeTransform } from '@angular/core'

@Pipe({name: 'money'})
export class MoneyPipe implements PipeTransform {
    
    transform(value: string) {
        var formatter = new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'})
        return formatter.format(parseFloat(value));
    }
    
}

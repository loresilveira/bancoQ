/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;


public class ContaPoupanca extends Conta{
    public ContaPoupanca(int numeroConta) {
        super(numeroConta);
    }
     @Override
    public boolean valorDisponivel(double valor) {
        return valor <=saldo;
    }

    @Override
 public String obterDadosConta(){
           return "Número: " + getNumeroConta() + " Saldo: " + getSaldo();
    }

 

  
    
}

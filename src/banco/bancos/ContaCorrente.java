/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;


public class ContaCorrente extends Conta{
    private double limite;
    private ContaPoupanca contaPoupancaVinculada;

    public ContaCorrente(int numeroConta, double limite, ContaPoupanca contaPoupancaVinculada) {
        super(numeroConta);
        this.limite = limite;
        this.contaPoupancaVinculada = contaPoupancaVinculada;
    }
    public ContaCorrente(int numeroConta, double limite) {
        super(numeroConta);
        this.limite = limite;
    }

    public void setContaPoupancaVinculada(ContaPoupanca contaPoupancaVinculada) {
        this.contaPoupancaVinculada = contaPoupancaVinculada;
    }
    
    public ContaPoupanca getContaPoupancaVinculada() {
        return this.contaPoupancaVinculada;
    }
    
    public void addContaPoupancaVinculada(int numeroConta){
        this.contaPoupancaVinculada = new ContaPoupanca(numeroConta);
    }       
    @Override
    public boolean valorDisponivel(double valor) {
        return valor <=(saldo+limite);
    }
    
    @Override
    public String obterDadosConta(){
           if (!(contaPoupancaVinculada==null))return "Número: " + getNumeroConta() + " Limite disponivel: " + (limite + getSaldo()) + " Saldo: " + getSaldo() + " CONTA POUPANÇA: " + contaPoupancaVinculada.obterDadosConta(); else return "Número: " + getNumeroConta() + " Limite disponivel: " + (limite + getSaldo()) + " Saldo: " + getSaldo();
           
    }
 
    
}

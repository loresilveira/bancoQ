/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;


public abstract class Conta {

    private int numeroConta;
    double saldo;

    public Conta(int numeroConta) {
        this.numeroConta = numeroConta;

    }

    public double getSaldo() {
        return saldo;
    }

    public void receberValor(double valor) {
        saldo = saldo + valor;
    }

    public void enviarValor(double valor) {
        if (valorDisponivel(valor)) {
            saldo = saldo - valor;
        }
    }

    public abstract boolean valorDisponivel(double valor);

    public abstract String obterDadosConta();

    public int getNumeroConta() {
        return numeroConta;
    }

}

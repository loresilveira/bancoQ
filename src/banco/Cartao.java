/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;


public class Cartao {

    private int numeroCartao;
    private int numeroAgencia;
    private int numeroConta;
    private String nomeCliente;
    private int cpf;
    private int codigoBanco;


    public Cartao(int numeroCartao, int numeroAgencia, int numeroConta, String nomeCliente, int cpf, int codigoBanco) {
        this.numeroCartao = numeroCartao;
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.codigoBanco = codigoBanco;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
    
}

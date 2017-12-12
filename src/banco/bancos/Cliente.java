/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;


public class Cliente {

    private String nome;
    private int cpf;
    private int senha;
    private Conta conta;

    public Cliente(String nome, int cpf, int senha, Conta conta) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    int getCpf() {
        return cpf;
    }

    int getSenha() {
        return senha;
    }

    public String obterDadosCliente() {
        return ("CLIENTE nome: " + nome + " CONTA CORRENTE " + conta.obterDadosConta());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;

import java.util.ArrayList;


public class Agencia {

    private ArrayList<Cliente> clientes;
    private ArrayList<Conta> contas;
    private int numeroAgencia;
    private String endereco;

    public Agencia(int numeroAgencia, String endereco) {
        this.numeroAgencia = numeroAgencia;
        this.endereco = endereco;
        this.clientes = new ArrayList<Cliente>();
        this.contas = new ArrayList<Conta>();
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public Conta buscarConta(int numeroConta) {
        
        Conta contaLocalizada = null;
        int controlador;
        for (controlador = 0; controlador < contas.size(); controlador = controlador + 1) {
            if (numeroConta == contas.get(controlador).getNumeroConta()) {
                contaLocalizada = contas.get(controlador);
            }
        }
        if (contaLocalizada == null) {
            System.out.println("Conta nÃ£o encontrada");
        }
        //if (!(contaLocalizada==null))System.out.println("Conta encontrada");
        return contaLocalizada;
    }

    public Cliente buscarCliente(int cpf) {
        Cliente clienteLocalizado = null;
        int controlador;
        for (controlador = 0; controlador < clientes.size(); controlador = controlador + 1) {
            if (cpf == clientes.get(controlador).getCpf()) {
                clienteLocalizado = clientes.get(controlador);
            }
        }
        if (clienteLocalizado == null) {
            System.out.println("Cliente nÃ£o encontrado");
        }
        //if (!(clienteLocalizado==null))System.out.println("Cliente encontrado"); 
        return clienteLocalizado;

    }

    public boolean autenticarCliente(Cliente cliente, int senha) {
        return cliente.getSenha() == senha;
    }

    public void addConta(Conta conta) {
        contas.add(conta);
    }

    public void addContaCorrente(int numeroConta, int senha, double limite) {
        contas.add(new ContaCorrente(numeroConta, limite));
    }

    public void addContaCorrente(ContaCorrente contaCorrente) {
        contas.add(contaCorrente);
    }

    public void addContaPolpanca(ContaPoupanca contaPoupanca) {
        contas.add(contaPoupanca);
    }

    public void addContaPolpanca(int numeroConta, int senha) {
        contas.add(new ContaPoupanca(numeroConta));
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void addCliente(String nome, int cpf, int senha, Conta conta) {
        clientes.add(new Cliente(nome, cpf, senha, conta));
    }

    public void carregarBD() {
        Conta conta01 = new ContaCorrente(1, 1000);
        addConta(conta01);
        Conta conta21 = new ContaPoupanca(21);
        addConta(conta21);
        Conta conta02 = new ContaCorrente(2, 2000, (ContaPoupanca) conta21);
        addConta(conta02);
        Cliente cliente01 = new Cliente("João Feijão", 1, 1, conta01);
        addCliente(cliente01);
        Cliente cliente02 = new Cliente("Maria Feijão", 2, 2, conta02);
        addCliente(cliente02);
    }
}

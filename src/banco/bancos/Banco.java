/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;

import banco.Autorizavel;
import java.util.ArrayList;
import caixa.Cadastravel;
import excecoes.ExceptionSaqueNaoAutorizado;
import excecoes.ExceptionTransferenciaInvalida;
import util.Mensagens;

public class Banco implements Cadastravel {

    private int codigoBanco;
    private String nome;
    private ArrayList<Agencia> agencias;

    public Banco(int codigoBanco, String nome) {
        this.codigoBanco = codigoBanco;
        this.nome = nome;
        this.agencias = new ArrayList<Agencia>();
    }

    public Banco(int codigo, String nome, ArrayList<Agencia> agencias) {
        this.codigoBanco = codigo;
        this.nome = nome;
        this.agencias = agencias;
    }

    @Override
    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void addAgencia(int numeroAgencia, String endereco) {
        agencias.add(new Agencia(numeroAgencia, endereco));
    }

    public void addAgencia(Agencia agencia) {
        agencias.add(agencia);
    }

    public Agencia buscarAgencia(int numeroAgencia) {
        Agencia agenciaLocalizada = null;
        
        for (int controlador = 0; controlador < agencias.size(); controlador = controlador + 1) {
            if (agencias.get(controlador).getNumeroAgencia() == numeroAgencia) {
                agenciaLocalizada = agencias.get(controlador);
            }
        }
        if (agenciaLocalizada == null) {
            System.out.println(Mensagens.AGENCIA_NAO_ENCONTRADA);
        }
        // if (!(agenciaLocalizada==null))System.out.println("Agencia encontrada");
        return agenciaLocalizada;
    }

    @Override
    public void autorizarTransferencia(Autorizavel autorizavel, int numeroAgenciaReceptor, int numeroContaReceptor, double valor) throws ExceptionTransferenciaInvalida {
        
        boolean autorizado = false;
        if (autenticarCliente(autorizavel)) autorizado = true;
        if((buscarAgencia(numeroAgenciaReceptor)!=null)&&(buscarAgencia(autorizavel.informarNumeroAgencia())!=null)){
        
            Conta contaReceptor = buscarAgencia(numeroAgenciaReceptor).buscarConta(numeroContaReceptor);
            Conta conta = buscarAgencia(autorizavel.informarNumeroAgencia()).buscarConta(autorizavel.informarNumeroConta());
            if((conta!=null)&&(contaReceptor!=null)){
            if (conta.valorDisponivel(valor)) {
                if (conta instanceof ContaPoupanca) {
                    if (contaReceptor instanceof ContaCorrente) {
                        if (((ContaCorrente) contaReceptor).getContaPoupancaVinculada() != null) {
                            if (((ContaCorrente) contaReceptor).getContaPoupancaVinculada().getNumeroConta() == conta.getNumeroConta()) {
                                autorizado = true;
                            }
                        }
                    }
                    if (contaReceptor instanceof ContaPoupanca) {
                        autorizado = false;
                    }
                    if (contaReceptor == null) {
                        autorizado = false;
                    }
                } else {
                    if (contaReceptor instanceof ContaCorrente) {
                        autorizado = true;
                    }
                    if (contaReceptor instanceof ContaPoupanca) {
                        autorizado = true;
                    }

                }
                if (conta.valorDisponivel(valor)) {
                    autorizado = true;
                }
            } else {
                autorizado = false;
            }
        } else {
            autorizado = false;
        }
        }
        if (autorizado == false) {
            throw new ExceptionTransferenciaInvalida(Mensagens.TRANSFERENCIA_INVALIDA);
        }
    
    }
    
    @Override
    public void realizarRetirada(Autorizavel autorizavel, double valor) throws ExceptionSaqueNaoAutorizado {
        Conta conta;
        if(buscarAgencia(autorizavel.informarNumeroAgencia())!=null){
        conta = buscarAgencia(autorizavel.informarNumeroAgencia()).buscarConta(autorizavel.informarNumeroConta());
        if(conta!=null){
        if (autenticarCliente(autorizavel)) {

            if (!conta.valorDisponivel(valor)) {
                throw new ExceptionSaqueNaoAutorizado(Mensagens.TRANSFERENCIA_INVALIDA);
            } else {
                conta.enviarValor(valor);
            }
        }
        }
    }
    }
    @Override
    public boolean realizarDeposito(int numeroAgencia, int numeroConta, double valor) {
        boolean retorno = false;
        if(buscarAgencia(numeroAgencia)!=null){
            if(buscarAgencia(numeroAgencia).buscarConta(numeroConta)!=null){
        buscarAgencia(numeroAgencia).buscarConta(numeroConta).receberValor(valor);
        retorno = true;
        }
        }
        return retorno;
    }

    @Override
    public String obterDadosCliente(Autorizavel autorizavel) {
        String retorno= "";
        if(buscarAgencia(autorizavel.informarNumeroAgencia())!=null){
        if(buscarAgencia(autorizavel.informarNumeroAgencia()).buscarCliente(autorizavel.informarCpf())!=null){    
        if (autenticarCliente(autorizavel)) {
            retorno = buscarAgencia(autorizavel.informarNumeroAgencia()).buscarCliente(autorizavel.informarCpf()).obterDadosCliente();
        } else {
            retorno = Mensagens.ACESSO_NEGADO;
        }
    }
        }
        return retorno;
    }
    @Override
    public void carregarBD() {
        Agencia agencia01 = new Agencia(01, "Rua Monte Pascoal, Salvador Bahia");
        Agencia agencia02 = new Agencia(02, "Rua Monte Pascoal, Salvador Bahia");
        agencia01.carregarBD();
        agencia02.carregarBD();
        addAgencia(agencia01);
        addAgencia(agencia02);
    }

    @Override
    public boolean autenticarCliente(Autorizavel autorizavel) {
        
        boolean retorno = false;
                if(buscarAgencia(autorizavel.informarNumeroAgencia())!=null){

        Cliente cliente = buscarAgencia(autorizavel.informarNumeroAgencia()).buscarCliente(autorizavel.informarCpf());
        Conta conta = buscarAgencia(autorizavel.informarNumeroAgencia()).buscarConta(autorizavel.informarNumeroConta());
        if((conta!=null)&&(cliente!=null)){
        if (conta instanceof ContaCorrente) {
            if ((cliente.getSenha() == autorizavel.informarSenha()) && (cliente.getConta().getNumeroConta() == autorizavel.informarNumeroConta())) {
                retorno = true;
            }
        }else{
        if (conta instanceof ContaPoupanca) {
            if ((cliente.getSenha() == autorizavel.informarSenha()) && (((ContaCorrente) cliente.getConta()).getContaPoupancaVinculada().getNumeroConta() == autorizavel.informarNumeroConta())) {
                retorno = true;
            }
        }
        }
        }
        }
        return retorno;
    }

}

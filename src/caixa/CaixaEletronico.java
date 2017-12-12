/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa;

import banco.Autorizavel;
import banco.bancos.Banco;
import java.util.ArrayList;
import banco.Cartao;
import excecoes.ExceptionSaqueNaoAutorizado;
import excecoes.ExceptionTransferenciaInvalida;
import util.Mensagens;

public class CaixaEletronico implements Autorizavel {

    private Cartao cartao;
    private int senha;
    private ArrayList<Cadastravel> bancosCadastrados;

    public CaixaEletronico(String endereco, Cartao cartao, int senha, ArrayList<Cadastravel> bancosCadastrados) {
        this.senha = senha;
        this.cartao = cartao;
        this.bancosCadastrados = bancosCadastrados;
    }

    public CaixaEletronico(String endereco, Cartao cartao, int senha) {
        this.senha = senha;
        this.cartao = cartao;
        this.bancosCadastrados = new ArrayList<Cadastravel>();
    }

    public CaixaEletronico() {
        this.senha = -1;
        this.cartao = null;
        this.bancosCadastrados = new ArrayList<Cadastravel>();
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    @Override
    public int informarSenha() {
        return senha;
    }

    @Override
    public int informarNumeroAgencia() {
        return cartao.getNumeroAgencia();
    }

    @Override
    public int informarNumeroConta() {
        return cartao.getNumeroConta();
    }

    @Override
    public int informarCpf() {
        return cartao.getCpf();
    }

    @Override
    public int informarCodigoBanco() {
        return cartao.getCodigoBanco();
    }

    public void cadastrarBanco(Cadastravel cadastrado) {
        bancosCadastrados.add(cadastrado);
    }

    public Cadastravel buscarBanco(int codigoBanco) {
        Cadastravel bancoEncontrado = null;
        int controlador;
        for (controlador = 0; bancosCadastrados.size() > controlador; controlador = controlador + 1) {
            if (bancosCadastrados.get(controlador).getCodigoBanco() == codigoBanco) {
                bancoEncontrado = (Banco) bancosCadastrados.get(controlador);
            }
        }
        if (bancoEncontrado == null) {
            System.out.println(Mensagens.BANCO_NAO_ENCONTRADO);
        }
        // if (!(bancoEncontrado==null))System.out.println("Banco encontrado");
        return bancoEncontrado;
    }

    public void requisitarTransferencia(int codigoBancoReceptor, int numeroAgenciaReceptor, int numeroContaReceptor, double valor) {
 
        Cadastravel selecionarBanco;
        Cadastravel selecionarBancoReceptor;
        selecionarBanco = buscarBanco(informarCodigoBanco());
        selecionarBancoReceptor = buscarBanco(codigoBancoReceptor);
        if((selecionarBanco!=null)&&(selecionarBancoReceptor!=null)){
            
        try {
            selecionarBanco.autorizarTransferencia(this, numeroAgenciaReceptor, numeroContaReceptor, valor);
            selecionarBanco.realizarRetirada(this, valor);
            selecionarBancoReceptor.realizarDeposito(numeroAgenciaReceptor, numeroContaReceptor, valor);
            
            System.out.println(Mensagens.TRANSFERENCIA_REALIZADA_COM_SUCESSO);
        } catch (ExceptionTransferenciaInvalida e) {
            System.out.println(e.getMessage());
        } catch (ExceptionSaqueNaoAutorizado ex) {
            System.out.println(ex.getMessage());
        }
    }   
    
    }

    public void requisitarSaque(double valor) {

        Cadastravel cadastrado;
        cadastrado = buscarBanco(informarCodigoBanco());
        if(cadastrado!=null){
        if (!cadastrado.autenticarCliente(this)) {
            
        } else {
            try {
                cadastrado.realizarRetirada(this, valor);
                
                System.out.println(Mensagens.SAQUE_REALIZADO_COM_SUCESSO);
            } catch (ExceptionSaqueNaoAutorizado ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        }
        }
    

    public void requisitarDeposito(int codigoBanco, int numeroAgencia, int numeroConta, double valor) {
        Cadastravel cadastrado;
        cadastrado = buscarBanco(codigoBanco);
        cadastrado.realizarDeposito(numeroAgencia, numeroConta, valor);
        
        System.out.println(Mensagens.DEPOSITO_REALIZADO_COM_SUCESSO);
    }

    public void requisitarDeposito(double valor) {

        Cadastravel cadastrado;
        cadastrado = buscarBanco(informarCodigoBanco());
        if(cadastrado!=null){
        if (!cadastrado.autenticarCliente(this)) {
        } else {
            cadastrado.realizarDeposito(informarNumeroAgencia(), informarNumeroConta(), valor);
        }
        }
    }

    public String obterDadosCliente() {
        String retorno = null;
        Cadastravel cadastrado;
        cadastrado = buscarBanco(informarCodigoBanco());
        if (cadastrado!=null){
        if (!cadastrado.autenticarCliente(this)) {
            return "Acesso não autorizado";

        } else {
            retorno = cadastrado.obterDadosCliente(this);

        }
    }else{
            retorno = "";    
        }
        return retorno;
    }

    public void carregarBD() {
        Cadastravel banco01 = new Banco(01, "Rua Monte Pascoal, Salvador Bahia");
        cadastrarBanco(banco01);
        banco01.carregarBD();
    }

}

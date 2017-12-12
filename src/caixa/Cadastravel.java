/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa;

import banco.Autorizavel;
import excecoes.ExceptionSaqueNaoAutorizado;
import excecoes.ExceptionTransferenciaInvalida;


public interface Cadastravel {
   public abstract String obterDadosCliente(Autorizavel autorizavel);
   public abstract void autorizarTransferencia(Autorizavel autorizavel, int numeroAgenciaReceptor, int numeroContaReceptor, double valor) throws ExceptionTransferenciaInvalida;  
   public abstract boolean realizarDeposito(int numeroAgencia, int numeroConta, double valor);
   public abstract void realizarRetirada(Autorizavel autorizavel, double valor) throws ExceptionSaqueNaoAutorizado;
   public abstract boolean autenticarCliente(Autorizavel autorizavel);
   public abstract int getCodigoBanco();
   public abstract void carregarBD();

    
}

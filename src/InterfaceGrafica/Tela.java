package InterfaceGrafica;


import banco.Cartao;
import caixa.CaixaEletronico; 


public class Tela {
    public static void main(String[] args) {

        Cartao cartao = new Cartao(111,1,1,"",1,1);
        Cartao cartao2 = new Cartao(112,1,2,"",2,1);
        //    public Cartao(int numeroCartao, int numeroAgencia, int numeroConta, String nomeCliente, int cpf, int codigoBanco) {

        CaixaEletronico caixaEletronico = new CaixaEletronico();
        caixaEletronico.carregarBD();
        caixaEletronico.setCartao(cartao);
        caixaEletronico.setSenha(1);
        

       caixaEletronico.requisitarDeposito(3000); 
       caixaEletronico.requisitarSaque(10); 
       System.out.println(caixaEletronico.obterDadosCliente());       
       caixaEletronico.requisitarTransferencia(1, 1, 2, 2500);
       System.out.println(caixaEletronico.obterDadosCliente());
       System.out.println(caixaEletronico.obterDadosCliente());   
       caixaEletronico.setCartao(cartao2);
       caixaEletronico.setSenha(2);        
       System.out.println(caixaEletronico.obterDadosCliente());   
        
     }
}
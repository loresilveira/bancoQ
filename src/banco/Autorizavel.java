/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;


public interface Autorizavel {

    public abstract int informarSenha();
    public abstract int informarNumeroAgencia();
    public abstract int informarNumeroConta();
    public abstract int informarCpf();
    public abstract int informarCodigoBanco();
    
    /**
     *
     * @return
     */
    
}

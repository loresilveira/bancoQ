/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bancos;

import java.util.ArrayList;


public class Titular {

    private ArrayList<Dependente> dependentes;
    private double renda;

    public Titular(ArrayList<Dependente> dependentes, double renda) {
        this.dependentes = dependentes;
        this.renda = renda;
    }

}

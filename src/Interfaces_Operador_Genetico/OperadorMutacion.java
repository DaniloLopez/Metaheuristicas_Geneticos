/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces_Operador_Genetico;

import individuo.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface OperadorMutacion {
  
    /**
     * cambiar dos posiciones tomadas aleatoriamente en un vector
     * @param pob_individuo
     * @param probabilidad
     * @param varianza
     * @return 
     */
    public ArrayList<Individuo> mutar (ArrayList<Individuo> pob_individuo, double probabilidad, int varianza);
      
}
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
 * @author Danilo
 */
public interface OperadorSolucion {
    
    public Individuo encontrarSolucion(ArrayList<Individuo> poblacion);
}

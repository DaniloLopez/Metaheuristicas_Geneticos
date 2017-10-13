package Operadores_Mochila;

import Interfaces_Operador_Genetico.OperadorSolucion;
import funciones.Funcion;
import individuo.Individuo;
import individuo.Individuo_funcion;
import individuo.Individuo_mochila;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class SolucionMochila implements OperadorSolucion {

    @Override
    public Individuo encontrarSolucion(ArrayList<Individuo> poblacion) {        
        for (Individuo ind : poblacion) {            
            Individuo_mochila indMoc = (Individuo_mochila)ind;
            if(indMoc.validarSoluionIdeal())
                return ind;
        }
        return null;
    }

}

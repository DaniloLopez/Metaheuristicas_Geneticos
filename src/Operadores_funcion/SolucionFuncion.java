package Operadores_funcion;

import Interfaces_Operador_Genetico.OperadorSolucion;
import funciones.Funcion;
import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class SolucionFuncion implements OperadorSolucion{

    @Override
    public Individuo encontrarSolucion(ArrayList<Individuo> poblacion) {
        Funcion fun = ((Individuo_funcion)poblacion.get(0)).getFuncion();
        for (Individuo ind : poblacion) {            
            if(fun.validateIdealSolution(ind))
                return ind;
        }
        return null;
    }

}

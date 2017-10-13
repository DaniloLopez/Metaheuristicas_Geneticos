package Operadores_funcion;

import Interfaces_Operador_Genetico.OperadorGenPoblacion;
import Utilidades.Aleatorio;
import funciones.Funcion;
import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.ArrayList;
import metaheuristicas_geneticos.Metaheuristicas_Geneticos;

/**
 *
 * @author Unicauca
 */
public class GenerarPoblacionFuncion <T> implements OperadorGenPoblacion{

    @Override
    public void generarPoblacion(int longitud, int tamanio_poblacion, ArrayList PoblacionInicial, Object funcion) {
        Funcion fun = (Funcion)funcion;
        Aleatorio random = new Aleatorio(4);
        double min = fun.getMin();
        double max = fun.getMax();
        for (int i = 0; i < tamanio_poblacion; i++) { 
            Double[] vector = random.vectorAleatorioDecimalProb(min, max, longitud, 1, 5);
            PoblacionInicial.add(new Individuo_funcion(vector, fun,Metaheuristicas_Geneticos.prob, 
                    Metaheuristicas_Geneticos.radio, Metaheuristicas_Geneticos.paso));            
        }
    }

}

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
public class GenerarPoblacionFuncion implements OperadorGenPoblacion{

    @Override
    public void generarPoblacion(int longitud, int tamanio_poblacion, ArrayList<Individuo> PoblacionInicial, Funcion funcion) {
        Aleatorio random = new Aleatorio(4);
        double min = funcion.getMin();
        double max = funcion.getMax();
        for (int i = 0; i < tamanio_poblacion; i++) { 
            Double[] vector = random.vectorAleatorioDecimalProb(min, max, longitud, 1, 5);
            PoblacionInicial.add(new Individuo_funcion(vector, funcion,Metaheuristicas_Geneticos.prob, 
                    Metaheuristicas_Geneticos.radio, Metaheuristicas_Geneticos.paso));            
        }
        
    }

}

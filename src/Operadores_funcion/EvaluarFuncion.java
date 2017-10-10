package Operadores_funcion;

import Interfaces_Operador_Genetico.OperadorEvaluacion;
import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class EvaluarFuncion implements OperadorEvaluacion{

    @Override
    public void EvaluarPoblacion(int longitudCromosoma, int tamanio_poblacion, ArrayList<Individuo> PoblacionInicial) {
        for (Individuo individuo : PoblacionInicial) {
            ((Individuo_funcion)individuo).calcularFitness();
        }
     }

}

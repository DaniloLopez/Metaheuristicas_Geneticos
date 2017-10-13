package Operadores_funcion;

import individuo.Individuo;
import Interfaces_Operador_Genetico.OperadorEvaluarMejorIndividuo;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class EvaluarMejorIndividuoFuncion implements OperadorEvaluarMejorIndividuo{

    @Override
    public boolean evaluar(Individuo best, Individuo ind) {
        return best.getFitness() < ind.getFitness();
    }

}

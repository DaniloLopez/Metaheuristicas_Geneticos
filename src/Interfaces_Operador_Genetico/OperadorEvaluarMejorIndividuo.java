package Interfaces_Operador_Genetico;

import individuo.Individuo;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public interface OperadorEvaluarMejorIndividuo {
    
    public boolean evaluar(Individuo best, Individuo ind);

}

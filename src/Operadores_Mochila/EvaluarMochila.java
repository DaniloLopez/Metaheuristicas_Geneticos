package Operadores_Mochila;

import Interfaces_Operador_Genetico.OperadorEvaluacion;
import individuo.Individuo;
import individuo.Individuo_mochila;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class EvaluarMochila  implements OperadorEvaluacion{

    @Override
    public void EvaluarPoblacion(int cantidad_reinas, int tamanio_poblacion, ArrayList<Individuo> PoblacionInicial) {
        for (Individuo individuo : PoblacionInicial) {
            ((Individuo_mochila)individuo).calcularFitness();
        }
    }

}

package Operadores_en_Comun;

import Interfaces_Operador_Genetico.OperadorCruce;
import Utilidades.Aleatorio;
import individuo.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class CruceUniform<T> implements OperadorCruce{

    @Override
    public ArrayList<Individuo> cruzar(Individuo padre, Individuo madre) {
        Aleatorio al = new Aleatorio(0);
        ArrayList<Individuo> hijos = new ArrayList<>();        
        double p = 0.5;
        
        Individuo hijo1 = padre.copy();
        Individuo hijo2 = madre.copy();
        T[] vector1 = (T[])padre.getCromosoma();
        T[] vector2 = (T[])madre.getCromosoma();
        
        for (int i = 0; i < vector1.length; i++) {
            if(p >= al.nextAleatorio(1)){
                T aux = vector1[i];
                vector1[i] = vector2[i];
                vector2[i] = aux;
            }
        }                
        
        hijos.add(hijo1);
        hijos.add(hijo2);
        return hijos;
    }

}

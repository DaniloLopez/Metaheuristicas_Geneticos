package Operadores_en_Comun;

import Interfaces_Operador_Genetico.OperadorCruce;
import Utilidades.Aleatorio;
import individuo.Individuo;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Unicauca
 */
public class CruceTwoPoint<T> implements OperadorCruce{

    @Override
    public ArrayList<Individuo> cruzar(Individuo padre, Individuo madre) {
        Individuo hijo1 = padre.copy();
        Individuo hijo2 = madre.copy();
        T[] vector1 = (T[])padre.getCromosoma();
        T[] vector2 = (T[])madre.getCromosoma();        
        
        ArrayList<Individuo> hijos = new ArrayList<>();
        Aleatorio al = new Aleatorio(9);
        int c = al.aleatorioEntero(0, vector1.length-1);
        int d;
        do{
            d = al.aleatorioEntero(0, vector1.length-1);
        }while(c == d);
                
        if(c > d){
            int aux = c;
            c = d;
            d = aux;
        }        
        for (int i = c; i < d; i++) {
            T aux = vector1[i];
            vector1[i] = vector2[i];
            vector2[i] = aux;
        }
                
        hijos.add(hijo1);
        hijos.add(hijo2);
        return hijos;
    }

}

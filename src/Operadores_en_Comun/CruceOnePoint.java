package Operadores_en_Comun;

import Interfaces_Operador_Genetico.OperadorCruce;
import Utilidades.Aleatorio;
import individuo.Individuo;
import java.util.ArrayList;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Unicauca
 */
public class CruceOnePoint<T> implements OperadorCruce{

    //funcion para cruzar con un punto
    @Override
    public ArrayList<Individuo> cruzar(Individuo padre, Individuo madre) {
        Individuo hijo1 = padre.copy();
        Individuo hijo2 = madre.copy();
        T[] vector1 = (T[])padre.getCromosoma();
        T[] vector2 = (T[])madre.getCromosoma();
        
        Aleatorio al = new Aleatorio(0);
        ArrayList<Individuo> hijos = new ArrayList<>();
        
        int c;
        do{
            c = al.aleatorioEntero(0, vector1.length-1);
        }while(c == 0);                        
                
        for (int i = c; i < vector1.length; i++) {
            T aux = vector1[i];
            vector1[i] = vector2[i];
            vector2[i] = aux;
        }
        hijos.add(hijo1);
        hijos.add(hijo2);
        
        return hijos;
    }       
}

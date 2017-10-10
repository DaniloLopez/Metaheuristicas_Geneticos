package Operadores_Mochila;

import Interfaces_Operador_Genetico.OperadorMutacion;
import Utilidades.Aleatorio;
import individuo.Individuo;
import individuo.Individuo_mochila;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class MutarMochila implements OperadorMutacion{

    Aleatorio al = new Aleatorio(0);
    
    @Override
    public ArrayList<Individuo> mutar(ArrayList<Individuo> pob_individuo, double probabilidad, int varianza) {        
        for (Individuo individuo : pob_individuo) {
            if(al.nextAleatorio(3) < probabilidad){//probabilidad de mutar el cromosoma dle individuo
                Individuo_mochila aux = (Individuo_mochila)individuo;
                aux.setCromosoma(mutarIndividuo((Boolean[])aux.getCromosoma(), probabilidad));
            }
        }
        return pob_individuo;
    }

    /**
     * mutar los genes de un individuo
     * @param cromosoma cromosoma del individuo
     * @param probabilidad probabilidad de mutar los genes
     * @return cromosoma del individuo mutado
     */        
    private Boolean[] mutarIndividuo(Boolean[] cromosoma, double probabilidad) {
        Boolean[] copia = cromosoma.clone();
        for (int i = 0; i < copia.length; i++) {
            if(al.nextAleatorio(3) < probabilidad){//probabilidad de mutar cada gen
                copia[i] = !copia[i];
            }
        }       
        return copia;
    }    

}

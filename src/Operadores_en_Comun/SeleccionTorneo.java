package Operadores_en_Comun;

import Interfaces_Operador_Genetico.OperadorSeleccion;
import Utilidades.Aleatorio;
import individuo.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class SeleccionTorneo implements OperadorSeleccion{

    @Override
    public Individuo seleccion(ArrayList<Individuo> PoblacionInicial, int t) {
        Aleatorio al = new Aleatorio(0);
        int p1 = al.aleatorioEntero(0, PoblacionInicial.size()-1);
        Individuo best = PoblacionInicial.get(p1);
        for (int i = 2; i < t; i++) {
            int p2;
            do{
                p2 = al.aleatorioEntero(0, PoblacionInicial.size()-1);
            }while(p1 == p2);
            Individuo next = PoblacionInicial.get(p2);
            if(best.getFitness() > next.getFitness()){
                best = next;
            }
        }
        return best;
    }

}

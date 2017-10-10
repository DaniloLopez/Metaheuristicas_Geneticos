package Operadores_en_Comun;

import Interfaces_Operador_Genetico.OperadorSeleccion;
import Utilidades.Aleatorio;
import individuo.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class SeleccionRestringidaFuncion implements OperadorSeleccion{

    @Override    
    public Individuo seleccion(ArrayList<Individuo> PoblacionInicial, int t) {
        Aleatorio al = new Aleatorio(t);
        int pos1 = al.aleatorioEntero(0, PoblacionInicial.size()-1);
        Individuo best = PoblacionInicial.get(pos1);
        
        int pos2 = al.aleatorioEntero(0, PoblacionInicial.size()-1);
        int pos3 = 0;
        do{
            pos3 = al.aleatorioEntero(0, PoblacionInicial.size()-1);
        }while(pos3 == pos2);
        
        ArrayList<Individuo> sub = evaluarSubGrupo(PoblacionInicial, pos2, pos3);
        
        Individuo far = sub.get(0);
        double dist = far.getFitness() - best.getFitness();
        for (Individuo individuo : sub) {
            if((individuo.getFitness() - best.getFitness()) > dist){
                best = individuo;
            }
        }        
        return best;
    }    

    public ArrayList<Individuo> evaluarSubGrupo(ArrayList<Individuo> PoblacionInicial, int ini, int fin) {
        ArrayList<Individuo> sub = new ArrayList<>();
        if(ini > fin){
            int aux = ini;
            ini = fin;
            fin = aux;
        }
        
        for (int i = ini; i <= fin; i++) {
            sub.add(PoblacionInicial.get(i));
        }
        return sub;
    }
}

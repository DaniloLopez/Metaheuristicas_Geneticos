package Operadores_Mochila;

import individuo.Individuo;
import Interfaces_Operador_Genetico.OperadorEvaluarMejorIndividuo;
import individuo.Individuo_mochila;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class EvaluarMejorIndividuoMochila  implements OperadorEvaluarMejorIndividuo{

    @Override       
    public boolean evaluar(Individuo best, Individuo ind) {
        //se evalua que individuo esta mas cerca del valor objetivo sin que lo sobrepase
        Individuo_mochila imb = (Individuo_mochila) best;
        Individuo_mochila imi = (Individuo_mochila) ind;
        
        double objb = imb.getMochila().getObj();
        double obji = imi.getMochila().getObj();
        
        double difb = objb - best.getFitness();
        double difi = obji - ind.getFitness();
        
        if(difb == 0) return false;        
        if(difi == 0) return true;        
        if(difb < 0 && difi < 0) return (difb < difi);               
        if(difb > 0 && difi < 0) return false;        
        if(difb < 0 && difi > 0) return true;                            
        if(difb > 0 && difi > 0) return (difb > difi);                            
        
        return false;
    }
    

}

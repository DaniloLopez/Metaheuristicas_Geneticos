package metaheuristicas_geneticos;

import Interfaces_Operador_Genetico.OperadorEvaluacion;
import Interfaces_Operador_Genetico.OperadorEvaluarMejorIndividuo;
import Interfaces_Operador_Genetico.OperadorGenPoblacion;
import Interfaces_Operador_Genetico.OperadorSolucion;
import Operadores_Mochila.Mochila;
import Utilidades.Aleatorio;
import individuo.Individuo;
import individuo.Individuo_mochila;
import individuo.Individuo_mochilaPSO;
import java.util.ArrayList;
import java.util.List;
import org.omg.CORBA.ObjectHelper;

/**
 * Optimización Binaria por Cúmulo de Partículas con Memoria (MBPSO)
 * @author Danilo López - dlopezs@unicauca.edu.co
 * @param <T> objeto tipo mochila que almacenará los articulos y la solucion deseada, si la hay
 */
public class Algorithm_PSO <T>{
    
    private int swarmize;
    private double alpha;
    private double beta;
    private double gamma;
    private double delta;
    private int epsilon;
    private int EFOs;       
    
    private Aleatorio rnd = new Aleatorio(0);
    
    int long_individuo;    
    T obj_helper;
    
    public Algorithm_PSO(double alpha, double beta, double gamma, double delta, int epsilon, int swarmize, int EFOs, int longitud, T helper) {
        this.alpha = alpha;         //a -> proporción de velocidad a retener
        this.beta = beta;           //b -> proporción de mejor personal para ser retenido
        this.gamma = gamma;         //r -> proporción de los mejores que se conservarán de los informantes
        this.delta = delta;         //s -> proporción de mejor global para ser retenido
        this.epsilon = epsilon;     //e -> tamaño de salto e de una partícula
        this.swarmize = swarmize;   //tamaño deseado del enjambre 
        this.EFOs = EFOs;           //nuero de repeticiones
        this.long_individuo = longitud;
        this.obj_helper = helper;
    }
    
    /**
     * iniciar el algoritmo para encntrar la solucion al problema de la mochila con algoitmo MBPSO
     * @return 
     */
    public Individuo_mochilaPSO correr(){
        
        ArrayList<Individuo_mochilaPSO> pob = new ArrayList<>();
        generarPoblacion(long_individuo, swarmize, pob, obj_helper);        
        Individuo_mochilaPSO best = null;        
        
        do{
            //evaluar cada individuo y fijar fitnes
            EvaluarPoblacion(pob);
            
            for (Individuo_mochilaPSO ind : pob) {
                if(best == null || evaluarMejor(best, ind)){
                    best = ind.copy();
                }
            }
            encontrarMejoresPasados(pob);
            
            for (Individuo_mochilaPSO ind : pob) {
                Individuo_mochilaPSO memoria = ind.getBest();
                Individuo_mochilaPSO mejorInformantes = mejorInformantes(gamma, pob);
                
                double[] cromVel = ind.getVelocidad();
                double[] aux = new double[cromVel.length];
                for (int i = 0; i< cromVel.length ; i++) {                    
                    
                    double b = rnd.aleatorioReal(0, beta);
                    double c = rnd.aleatorioReal(0, gamma);
                    double d = rnd.aleatorioReal(0, delta);
                    //http://www.cys.cic.ipn.mx/ojs/index.php/CyS/article/viewFile/2198/2102
                    aux[i] = cromVel[i]*alpha + 
                            b*(iguales(memoria.getVelocidad()[i], ind.getVelocidad()[i])) +
                            c*(iguales(mejorInformantes.getVelocidad()[i], ind.getVelocidad()[i])) +
                            d*(iguales(best.getVelocidad()[i], ind.getVelocidad()[i]));
                }                
                ind.setVelocidad(aux);
            }
            for (int i = 0; i< pob.size(); i++) {
                int[] pos = pob.get(i).getCromosoma();
                double[] vel = pob.get(i).getVelocidad();
                for (int j = 0; j < pos.length; j++) {
                    //epsilon deberia ser 1 para este problema
                    //pos[j] = pos[j] + epsilon*vel[j];                   
                    if(rnd.nextAleatorio(3) > vel[j]){
                        pos[j] = (pos[j] == 0) ? 1 : 0;
                    }                                        
                }
                //condicionar el alamacenamiento de la mejor posicion del individuo a travez del tiempo
                if(pob.get(i).getBest().getFitness() > pob.get(i).getFitness()){
                    pob.get(i).setBest(pob.get(i));
                }
            }
            
            Individuo_mochilaPSO solucion  = encontrarSolucion(pob);            
            
            if(solucion != null){                
                return solucion;
            } 
            EFOs --;            
        }while(EFOs != 0);
        
        return best;        
    }
    
    
    /**
     * encargado de ver si se aumenta la probabilidad de cambiar el cromosoma o por el contrario se disminuye enviando un -1
     * @param i numero 1
     * @param j numero 2
     * @return 1 si i es igual a j, de lo contrario -1
     */
    private int iguales(double i, double j){
        if(i == j)
            return 1;
        return -1;
    }

      
    /**
     * genera los individuos del enjambre
     * @param long_individuo cantidad del cromosoma del individuo(vector posicion)
     * @param swarmize cantidad del enjambre
     * @param PoblacionInicial referencia de la lista donde se almacenaran los resultados
     * @param obj_helper objeto mochila que posee la solucion y los articulos correspondientes
     */
    private void generarPoblacion(int long_individuo, int swarmize, ArrayList<Individuo_mochilaPSO> PoblacionInicial, T obj_helper) {
        Mochila mochi = (Mochila)obj_helper;
        Aleatorio random = new Aleatorio(4);
        for (int i = 0; i < swarmize; i++) { 
            int[] vector = random.vectorAleatorioBinarioEntero(long_individuo);
            double[] vel = random.vectorAleatorioDecimal2(0, swarmize, long_individuo);
            PoblacionInicial.add(new Individuo_mochilaPSO(vector, mochi, Metaheuristicas_Geneticos.prob));
            PoblacionInicial.get(i).setVelocidad(vel);            
        }
    }

    
    /**
     * invoac metodo calcularFitness para todos os individuos del enjambre     
     * @param pob poblacion del enjambre
     */
    private void EvaluarPoblacion( ArrayList<Individuo_mochilaPSO> pob) {
        pob.stream().forEach((individuo) -> {
            ((Individuo_mochilaPSO)individuo).calcularFitness();
        });
    }

    
    /**
     * evalua el individuo que tiene mejor fitness
     * @param best individuo 1
     * @param ind individuo 2
     * @return best si tienen un fitness mas bajo que ind
     */
    private boolean evaluarMejor(Individuo_mochilaPSO best, Individuo_mochilaPSO ind) {
        //se evalua que individuo esta mas cerca del valor objetivo sin que lo sobrepase
        Individuo_mochilaPSO imb = (Individuo_mochilaPSO) best;
        Individuo_mochilaPSO imi = (Individuo_mochilaPSO) ind;
        
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

    
    /**
     * escoje gamma informantes aleatooriamente del enjambre y retorna el mejor de esos gamma informates
     * @param gamma numero de informantes a tener en cuenta
     * @param pob enjambre de particulas
     * @return mejor individuo en el enjambre
     */
    private Individuo_mochilaPSO mejorInformantes(double gamma, ArrayList<Individuo_mochilaPSO> pob) {
        List<Integer> escojidos = new ArrayList<>();
        List<Individuo_mochilaPSO> inf = new ArrayList<>();
        int pos ;
        for (int i = 0; i < gamma; i++){
            do{
                pos = rnd.aleatorioEntero(0, pob.size());
            }while(escojidos.contains(pos));
            escojidos.add(pos);
            inf.add(pob.get(i).copy());
        }
        Individuo_mochilaPSO best = null;
        for (Individuo_mochilaPSO ind : inf) {
            if(best == null || (best.getFitness() > ind.getFitness()))
                best = ind;
        }
        return best;        
    }
            
    
    /**
     * verificar si entre la poblacion ya se encontro la solucion deseada
     * @param pob poblacion a evaluar
     * @return true si se encontro la solucion, de lo contrario false
     */
    private Individuo_mochilaPSO encontrarSolucion(ArrayList<Individuo_mochilaPSO> pob) {
        for (Individuo_mochilaPSO ind : pob) {            
            Individuo_mochilaPSO indMoc = (Individuo_mochilaPSO)ind;
            if(indMoc.validarSoluionIdeal())
                return ind;
        }
        return null;        
    }    

    private void encontrarMejoresPasados(ArrayList<Individuo_mochilaPSO> pob) {
        for (Individuo_mochilaPSO ind : pob) {
            if(ind.getBest() == null || ind.getBest().getFitness() > ind.getFitness()){
                ind.setBest(ind);
            }
        }        
    }

}

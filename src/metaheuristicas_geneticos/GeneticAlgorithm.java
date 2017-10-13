package metaheuristicas_geneticos;

import individuo.Individuo;
import Interfaces_Operador_Genetico.OperadorCruce;
import Interfaces_Operador_Genetico.OperadorEvaluacion;
import Interfaces_Operador_Genetico.OperadorEvaluarMejorIndividuo;
import Interfaces_Operador_Genetico.OperadorMutacion;
import Interfaces_Operador_Genetico.OperadorSeleccion;
import Interfaces_Operador_Genetico.OperadorSolucion;
import java.util.ArrayList;
import java.util.Comparator;
import Interfaces_Operador_Genetico.OperadorGenPoblacion;
import funciones.Funcion;

/**
 *
 * @author Unicauca
 */
public class GeneticAlgorithm <T>{

    //variables de operaciones geneticas
    OperadorGenPoblacion oper_poblacion;
    OperadorEvaluacion oper_evaluacion;
    OperadorSeleccion oper_seleccion_padre;
    OperadorSeleccion oper_seleccion_madre;
    OperadorCruce oper_cruce;
    OperadorMutacion oper_mutacion;    
    OperadorSolucion oper_solucion;
    OperadorEvaluarMejorIndividuo oper_evaluarIndividuo;
                
    //variables de entrada
    int cant_poblacion;
    int long_individuo;    
    int num_generaciones;
    double probabilidad;
    int elitismo;
            
    //poblacion para el algoritmo genetico
    ArrayList<Individuo> pob_inicial;
    T obj_helper;
    private int iteraciones;
         
    public GeneticAlgorithm(T funcion,
                    OperadorGenPoblacion poblacion, OperadorEvaluacion evaluacion, 
                    OperadorSeleccion seleccionP, OperadorSeleccion seleccionM, OperadorCruce cruce, 
                    OperadorMutacion mutacion, OperadorSolucion solucion, OperadorEvaluarMejorIndividuo mejorInd,
                    int cant_poblacion, int long_individuo, int num_generaciones, double probabilidad, int elitismo){
        
        this.obj_helper    = funcion;
        
        
        oper_poblacion  = poblacion;
        oper_evaluacion = evaluacion;        
        oper_cruce      = cruce;
        oper_mutacion   = mutacion;        
        oper_solucion   = solucion;
        oper_seleccion_padre = seleccionP;
        oper_seleccion_madre = seleccionM;
        oper_evaluarIndividuo = mejorInd;
        
        this.cant_poblacion     = cant_poblacion;
        this.long_individuo     = long_individuo;
        this.num_generaciones   = num_generaciones;
        this.probabilidad       = probabilidad;
        this.elitismo           = elitismo;
                    
        
    }
                            
    /**
     * Inicio del algoritmo genetico
     * @param u valor de u
     * @param k valor de k
     * @param EFOs numero de repeticiones
     * @return individuo con mejor aptitud
     */
    public Individuo correr(int u, int k, int EFOs){ 
        
        this.pob_inicial    = new ArrayList<>();
        //generar poblacion
        oper_poblacion.generarPoblacion(long_individuo, cant_poblacion, pob_inicial, obj_helper);     
        
        Individuo best = null;
                
        do{
            //evaluar cada individuo y fijar fitnes
            oper_evaluacion.EvaluarPoblacion(long_individuo, cant_poblacion, pob_inicial);            
            if(((T[])pob_inicial.get(0).getCromosoma()).length == 0)
                    System.out.println("");
            for (Individuo ind : pob_inicial) {
                if(best == null || oper_evaluarIndividuo.evaluar(best, ind)){
                    best = ind;
                }
            }
            ordenarLista(pob_inicial);//ordenar lista para fijar elitismo
            ArrayList<Individuo> hijos = new ArrayList<>(); //Q <- {}             
            for (int i = 0; i < elitismo; i++) {
                hijos.add(pob_inicial.get(i));
            }
            
            for (int i = 0; i < (cant_poblacion - elitismo)/2; i++) {                
                Individuo padre = oper_seleccion_padre.seleccion(pob_inicial, 2);         
                if(((T[])padre.getCromosoma()).length == 0)
                    System.out.println("");
                Individuo madre = oper_seleccion_madre.seleccion(pob_inicial, 2);                
                ArrayList<Individuo> hijosCruze = oper_cruce.cruzar(padre, madre);
                hijosCruze = oper_mutacion.mutar((ArrayList<Individuo>)hijosCruze, probabilidad, 2);
                
                hijos.addAll(hijosCruze);
            }
            pob_inicial = hijos;           
            //evaluar condicion parada
            Individuo solucion = oper_solucion.encontrarSolucion(pob_inicial);            
            if(solucion != null){                
                return solucion;
            }                                    
            EFOs--;            
            //System.out.println("Efo: " + EFOs +" alg: " + obj_helper.getNombreFuncion());
        }while(EFOs != 0);
        
        return best;
    }
    
    public void ordenarLista(ArrayList<Individuo> pob_ini){
        pob_ini.sort(new Comparator() {
            @Override
            public int compare(Object ind1, Object ind2) {
                if(((Individuo)ind1).getFitness() == ((Individuo)ind2).getFitness())return 0;
                if(((Individuo)ind1).getFitness() < ((Individuo)ind2).getFitness()) return -1;
                if(((Individuo)ind1).getFitness() > ((Individuo)ind2).getFitness()) return 1;
                return 0;
            }          
        });
    }
    
    
    public Object getFuncion(){
        return obj_helper;
    }
}
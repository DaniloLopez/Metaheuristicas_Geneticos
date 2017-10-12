package metaheuristicas_geneticos;

import Operadores_en_Comun.CruceTwoPoint;
import Operadores_en_Comun.SeleccionTorneo;
import Operadores_funcion.EvaluarFuncion;
import Operadores_funcion.GenerarPoblacionFuncion;
import Operadores_funcion.MutarFuncion;
import Operadores_funcion.SolucionFuncion;
import funciones.Ackley;
import funciones.Griewank;
import funciones.Rastrigin;
import funciones.Schwefel;
import funciones.Sphere;
import funciones.Step;
import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.ArrayList;
import java.util.Arrays;
import static metaheuristicas_geneticos.Metaheuristicas_Geneticos.prob;
import static metaheuristicas_geneticos.Metaheuristicas_Geneticos.radio;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Main_Funciones {

    
    /**
     * implementacion algoritmo genetico con el problema de optimizacion de funciones
     */
    public void geneticoFuncion(){
        ArrayList<GeneticAlgorithm> algoritmos = new ArrayList<>();
        int dimensiones = 10;
        int EFOs        = 5000;                                 
        int tamanioPob  = 20;
        int elitismo    = 2;        
        
        //instancias de la clase funcion
        Sphere esfera           = new Sphere(-100, 100, radio);
        Step step               = new Step(-100,100, radio);
        Schwefel schwefel       = new Schwefel(-100,100);
        Rastrigin rastrigin     = new Rastrigin(-5.12, 5.12);
        Griewank grienwank      = new Griewank(-600, 600);
        Ackley ackley           = new Ackley(-32, 32 );
                
        algoritmos.add(new GeneticAlgorithm(esfera, new GenerarPoblacionFuncion(), new EvaluarFuncion(), new SeleccionTorneo(), new SeleccionTorneo(), new CruceTwoPoint(), new MutarFuncion(),new SolucionFuncion(), tamanioPob, dimensiones, 2, prob, elitismo));
        algoritmos.add(new GeneticAlgorithm(step, new GenerarPoblacionFuncion(), new EvaluarFuncion(), new SeleccionTorneo(), new SeleccionTorneo(), new CruceTwoPoint(), new MutarFuncion(), new SolucionFuncion(), tamanioPob, dimensiones, 2, prob, elitismo));
        algoritmos.add(new GeneticAlgorithm(schwefel, new GenerarPoblacionFuncion(), new EvaluarFuncion(), new SeleccionTorneo(), new SeleccionTorneo(), new CruceTwoPoint(), new MutarFuncion(), new SolucionFuncion(), tamanioPob, dimensiones, 2, prob, elitismo));
        algoritmos.add(new GeneticAlgorithm(rastrigin, new GenerarPoblacionFuncion(), new EvaluarFuncion(), new SeleccionTorneo(), new SeleccionTorneo(), new CruceTwoPoint(), new MutarFuncion(), new SolucionFuncion(), tamanioPob, dimensiones, 2, prob, elitismo));
        algoritmos.add(new GeneticAlgorithm(grienwank, new GenerarPoblacionFuncion(), new EvaluarFuncion(), new SeleccionTorneo(), new SeleccionTorneo(), new CruceTwoPoint(), new MutarFuncion(), new SolucionFuncion(), tamanioPob, dimensiones, 2, prob, elitismo));
        algoritmos.add(new GeneticAlgorithm(ackley, new GenerarPoblacionFuncion(), new EvaluarFuncion(), new SeleccionTorneo(), new SeleccionTorneo(), new CruceTwoPoint(), new MutarFuncion(), new SolucionFuncion(), tamanioPob, dimensiones, 2, prob, elitismo)); 
        
        for (GeneticAlgorithm alg : algoritmos) {
            Individuo res = alg.correr(5, 2, EFOs);
            System.out.println("+++++ encontrado "+alg.getFuncion().getNombreFuncion() + ". res "+ Arrays.toString(((Individuo_funcion)res).getCromosoma()) +"  fitness: " + res.getFitness());
        }
    }
}

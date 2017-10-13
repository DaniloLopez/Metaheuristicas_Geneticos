package metaheuristicas_geneticos;

import Archivo.LeerArchivoMochila;
import Operadores_Mochila.EvaluarMejorIndividuoMochila;
import Operadores_Mochila.EvaluarMochila;
import Operadores_Mochila.GenerarPoblacionMochila;
import Operadores_Mochila.Mochila;
import Operadores_Mochila.MutarMochila;
import Operadores_Mochila.SolucionMochila;
import Operadores_en_Comun.CruceTwoPoint;
import Operadores_en_Comun.SeleccionRestringidaFuncion;
import Operadores_en_Comun.SeleccionTorneo;
import Operadores_funcion.EvaluarFuncion;
import Operadores_funcion.GenerarPoblacionFuncion;
import Operadores_funcion.MutarFuncion;
import Operadores_funcion.SolucionFuncion;
import individuo.Individuo_mochila;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static metaheuristicas_geneticos.Metaheuristicas_Geneticos.prob;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Main_Mochila {
    
    int dimensiones = 10;
    int EFOs        = 5000;                                 
    int tamanioPob  = 20;
    int elitismo    = 2;  
                 
    ArrayList<GeneticAlgorithm> algoritmos = new ArrayList<>();
    List<String> nombreArchivos;
    LeerArchivoMochila lam;
    List<Mochila> mochilas;
    
    public Main_Mochila() {
        nombreArchivos = new ArrayList<>();
        lam = new LeerArchivoMochila();
        mochilas = new ArrayList<>();                
        agregarnombreArchivos();
    }        
    
    /**
    * implementacion de algoritmo genetico para el problema de la mochila
    */
    public void geneticoMochila(){                
        //leer archivos de texto                     
        for (String nombreArchivo : nombreArchivos) {
            Mochila moc = lam.leerArchivo(nombreArchivo); 
            //System.out.println(moc.toString());
            mochilas.add(moc);            
        }               
        
        int  o = 0;
        for (Mochila mochila : mochilas) {            
            
            GeneticAlgorithm ga = new GeneticAlgorithm(mochila, new GenerarPoblacionMochila(), new EvaluarMochila(), new SeleccionTorneo(), new SeleccionRestringidaFuncion(), new CruceTwoPoint(), new MutarMochila(),new SolucionMochila(), new EvaluarMejorIndividuoMochila(),tamanioPob, mochila.getArticulos().size(), 2, prob, elitismo);            
            Individuo_mochila res = (Individuo_mochila)ga.correr(5, 2, EFOs);
            System.out.println("fitnes: " + res.getFitness() + ". Solucion" + Arrays.toString(res.getCromosoma()));
            if (o == 9){
                System.out.println("");
            }
            o++;
        }
        
    }

    private void agregarnombreArchivos() {
        nombreArchivos.add("f1.txt");
        nombreArchivos.add("f2.txt");
        nombreArchivos.add("f3.txt");
        nombreArchivos.add("f4.txt");
        nombreArchivos.add("f5.txt");
        nombreArchivos.add("f6.txt");
        nombreArchivos.add("f7.txt");
        nombreArchivos.add("f8.txt");
        nombreArchivos.add("f9.txt");
        nombreArchivos.add("f10.txt");
        nombreArchivos.add("Knapsack1.txt");
        nombreArchivos.add("Knapsack2.txt");
        nombreArchivos.add("Knapsack3.txt");
        nombreArchivos.add("Knapsack4.txt");
        nombreArchivos.add("Knapsack5.txt");
        nombreArchivos.add("Knapsack6.txt");                                
    }        
}

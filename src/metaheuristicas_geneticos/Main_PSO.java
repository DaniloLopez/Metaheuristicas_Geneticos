package metaheuristicas_geneticos;

import Archivo.LeerArchivoMochila;
import Operadores_Mochila.Mochila;
import individuo.Individuo_mochilaPSO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Main_PSO {
    
    private int dimensiones = 10;
    private int EFOs        = 5000;                                 
    private int tamanioPob  = 20;
    private int elitismo    = 2;  
                 
    private ArrayList<GeneticAlgorithm> algoritmos = new ArrayList<>();
    private List<String> nombreArchivos;
    private LeerArchivoMochila lam;
    private List<Mochila> mochilas;
    
    public Main_PSO() {
        nombreArchivos = new ArrayList<>();
        lam = new LeerArchivoMochila();
        mochilas = new ArrayList<>();                
        agregarnombreArchivos();
    }        
    
    /**
    * implementacion de algoritmo genetico para el problema de la mochila
    */
    public void geneticoPSO(){                
        //leer archivos de texto
        nombreArchivos.stream().map((nombreArchivo) -> lam.leerArchivo(nombreArchivo)).forEach((Mochila moc) -> {
            mochilas.add(moc);
        });               
        
        int  o = 0;
        for (Mochila mochila : mochilas) {            
            
            Algorithm_PSO alg_pso = new Algorithm_PSO(0.1, 0.8, 0.7, 0.6, 1, tamanioPob, EFOs, mochila.getSolucion().length, mochila);            
            Individuo_mochilaPSO res = (Individuo_mochilaPSO)alg_pso.correr();
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

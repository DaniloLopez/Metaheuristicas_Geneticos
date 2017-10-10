/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheuristicas_geneticos;

import Archivo.LeerArchivoMochila;
import Operadores_Mochila.Mochila;
import Operadores_en_Comun.CruceTwoPoint;
import Operadores_en_Comun.SeleccionTorneo;
import Operadores_funcion.EvaluarFuncion;
import Operadores_funcion.GenerarPoblacionFuncion;
import Operadores_funcion.MutarFuncion;
import Operadores_funcion.SolucionFuncion;
import funciones.*;
import individuo.Individuo;
import individuo.Individuo_funcion;
import individuo.Individuo_mochila;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Danilo
 */
public class Metaheuristicas_Geneticos {
            
    //probabilidad de adicionar ruido
    public static double  prob = 0.05;        
    //amplitud para calcular aleatorio
    public static double  radio = 0.9;
    public static int     paso    = 1;      
    
   /**
     * @param args the command line arguments
     */       
    public static void main(String[] args) {                                     
        
        //correr la implementacion para algoritmos geneticos orientado al problema de optimizacion de funciones
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        geneticoFuncion();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        
        //correr la implementacion para algoritmos geneticos orientado al problema de la mochila
        //======================================================================                
        //geneticoMochila();
        //======================================================================
        
    }//fin clase main    
    
    
    /**
     * implementacion algoritmo genetico con el problema de optimizacion de funciones
     */
    public static void geneticoFuncion(){
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
    
    /**
     * implementacion de algoritmo genetico para el problema de la mochila
     */
    public static void geneticoMochila(){
        String nombreArchivo = "f1.txt";
        LeerArchivoMochila lam = new LeerArchivoMochila();
        Mochila moc = lam.leerArchivo(nombreArchivo);
        
        
        System.out.println(moc.toString());
    }
    
}



















/*
//<editor-fold defaultstate="collapsed" desc="hill climbing">
        //leer datos del archivo de verificacion
        //LeerArchivoPrueba lap = new LeerArchivoPrueba();        
        //datos = lap.leerArchivo("2017-09-13-Proyecto-datos-regresion.txt");      
        //instancia de la clase Regresion
        //Regresion regPol  = new Regresion(min, max, datos);
        //algoritmos.add(new HillClimbing(regPol));                
                
        //vector de resultados        
        ArrayList<ResultadoIndividuo> res = new ArrayList();                
        ResultadoFuncion resFun;
        
        System.out.println("Calculando...");
        for (HillClimbingAbstract algHC : algoritmos) {                    
            for (int i = 0; i < cantPruebas; i++) {
                random = new Aleatorio(i);
                //generar vector  de aleatorios
                Double vectorAleatorios[] = random.vectorAleatorioDecimalProb(
                        algHC.getFuncion().getMin(), algHC.getFuncion().getMax(), 
                        dimensiones, 0.7, 1
                );
                
                double start = System.currentTimeMillis();
                
                //iniciar algoritmo hill climbing
                resFun = algHC.inicio(new Individuo_funcion(vectorAleatorios, algHC.getFuncion(), prob, radio, paso),EFOs);                
                
                double end = System.currentTimeMillis() - start;
                
                res.add(new ResultadoIndividuo( algHC.getNonmbreFuncion(),algHC.getClass().getSimpleName(), 
                                                resFun.getIteracion(), end, resFun.getResultado()));                
                
                System.out.println("+++++  Iteracion " + i + " res " + resFun.getResultado().toString() );                
            }
            objEstadistica.calcularEstadisticas(res, algHC, dimensiones, resFinal);
            //res.clear();
            System.out.println("********encontrado:  alg -> "+algHC.getClass().getSimpleName());
        }
        
        
        //escribir el resultado en el archivo
        boolean estado = objEscribirArchivo.excribirResultados(nombreArchivo, resFinal);                           
        if(estado){
            System.out.println("Programa finalizado.");
        }else{
            System.out.println("No se completo la escritura de resultados en el archivo.");
        }
        objEscribirArchivo.excribirSolucion(nombreArchivo, res);
//</editor-fold>
*/
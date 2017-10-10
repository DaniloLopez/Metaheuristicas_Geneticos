package Operadores_Mochila;

import Interfaces_Operador_Genetico.OperadorGenPoblacion;
import Utilidades.Aleatorio;
import funciones.Funcion;
import individuo.Individuo;
import individuo.Individuo_mochila;
import java.util.ArrayList;
import metaheuristicas_geneticos.Metaheuristicas_Geneticos;

/**
 *
 * @author Unicauca
 */
public class GenerarPoblacionMochila implements OperadorGenPoblacion{

    @Override
    public void generarPoblacion(int longitud, int tamanio_poblacion, ArrayList<Individuo> PoblacionInicial, Funcion funcion) {
        Aleatorio random = new Aleatorio(4);        
        for (int i = 0; i < tamanio_poblacion; i++) { 
            Boolean[] vector = random.vectorAleatorioBooleano(longitud);
            PoblacionInicial.add(new Individuo_mochila(vector, Metaheuristicas_Geneticos.prob));            
        }
    }
    
    

}

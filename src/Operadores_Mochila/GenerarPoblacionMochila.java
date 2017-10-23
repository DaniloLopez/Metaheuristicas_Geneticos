package Operadores_Mochila;

import Interfaces_Operador_Genetico.OperadorGenPoblacion;
import Utilidades.Aleatorio;
import individuo.Individuo_mochila;
import java.util.ArrayList;
import java.util.List;
import metaheuristicas_geneticos.Metaheuristicas_Geneticos;

/**
 *
 * @author Unicauca
 * @param <T>
 */
public class GenerarPoblacionMochila <T> implements OperadorGenPoblacion{
   
    /**
     * genera una poblacion aleatoria para la mochila
     * @param longitud
     * @param tamanio_poblacion
     * @param PoblacionInicial
     * @param superC
     */
    @Override
    public void generarPoblacion(int longitud, int tamanio_poblacion, ArrayList PoblacionInicial, Object superC) {
        Mochila mochi = (Mochila)superC;
        Aleatorio random = new Aleatorio(4);        
        for (int i = 0; i < tamanio_poblacion; i++) { 
            Boolean[] vector = random.vectorAleatorioBooleano(longitud);
            PoblacionInicial.add(new Individuo_mochila(vector, mochi, Metaheuristicas_Geneticos.prob));
        }
    }
    
    public void cuadrarVector(Mochila moc, boolean[] cromosoma){
        
        
    }
    
    public double calcularPesoObjetos(boolean[] cromosoma, Mochila moc){
        List<Articulo> arts = moc.getArticulos();
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            if(cromosoma[i]){
                sum += arts.get(i).getPeso();
            }
        }
        return sum;
    }
    
    

}

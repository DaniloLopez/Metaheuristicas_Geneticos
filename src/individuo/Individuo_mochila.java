package individuo;

import Utilidades.Aleatorio;
import funciones.Sphere;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Unicauca
 * @param <T>
 */
public class Individuo_mochila<T> implements Individuo {
    
    protected T[] cromosoma ;
    protected double fitness;        
    protected double probabilitiAddNoise;
        
    Aleatorio random = new Aleatorio(0);    

    public Individuo_mochila(T[] cromosoma, double probabilitiAddNoise) {
        this.cromosoma = cromosoma;                
        this.probabilitiAddNoise = probabilitiAddNoise;
        //calcularFitness();
    }
  
    @Override
    public T[] getCromosoma() {
        return cromosoma;
    }

    @Override
    public void setCromosoma(Object[] cromosoma) {
        this.cromosoma = (T[])cromosoma;
    }

    public double getProbabilitiAddNoise() {
        return probabilitiAddNoise;
    }

    public void setProbabilitiAddNoise(double probabilitiAddNoise) {
        this.probabilitiAddNoise = probabilitiAddNoise;
    }
    
    public void calcularFitness() {
        
    }  
  
    @Override
    public Individuo copy() {
        try {
            return (Individuo)this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Sphere.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    @Override
    public double getFitness() {
        return this.fitness;
    }

    @Override
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }
}

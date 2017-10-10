package individuo;

/**
 *
 * @author Unicauca
 * @param <T>
 */
public interface Individuo<T> {
    /**
     * genera una copia del tipoDato que recibe como parametro     
     * @return valor del individuo duplicado
     */
    public  Individuo copy();
   
    public  double getFitness() ;
    
    public  void setFitness(Double fitness);
    
    public T getCromosoma();
        
    public void setCromosoma(T[] cromosoma);       
    
}
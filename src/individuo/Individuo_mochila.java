package individuo;

import Operadores_Mochila.Articulo;
import Operadores_Mochila.Mochila;
import Utilidades.Aleatorio;
import funciones.Sphere;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Unicauca
 * @param <T>
 */
public class Individuo_mochila<T> implements Individuo, Cloneable {
    
    protected Boolean[] cromosoma ;
    protected double fitness;        
    protected double probAddRuido;
    private Mochila mochila ;

    public Individuo_mochila(Mochila mochila, double prob) {
        this.mochila = mochila;
        this.probAddRuido = prob;
    }

    public Individuo_mochila(Boolean[] vector, Mochila mochi, double prob) {
        cromosoma = vector;
        this.probAddRuido = prob;
        this.mochila = mochi;
    }
  
    @Override
    public Boolean[] getCromosoma() {
        return cromosoma;
    }

    @Override
    public void setCromosoma(Object[] cromosoma) {
        this.cromosoma = (Boolean[])cromosoma;
    }

    public double getProbAddRuido() {
        return probAddRuido;
    }

    public void setProbAddRuido(double probAddRuido) {
        this.probAddRuido = probAddRuido;
    }
    
    public void calcularFitness() {
        Double objAux;                       
        List<Articulo> moc = mochila.getArticulos();        
        objAux = calcularSuma(moc);       
        while(objAux > mochila.getK()){
            objAux = cuadrarMochila(moc);
            //System.out.println("cuadrando mochila");
        }     
        //System.out.println("k: " +mochila.getK() + " fitness: " + objAux);
        this.fitness = objAux;
    }        
    
    public double calcularSuma(List<Articulo> moc){
        double objAux = 0;
        for (int i= 0; i< cromosoma.length ; i++) {
            if(cromosoma[i]){
                objAux =  moc.get(i).getValor() + objAux;
            }
        }
        return objAux;
    }
    
    public double cuadrarMochila(List<Articulo> moc){
        Aleatorio al = new Aleatorio(0);
        int i = al.aleatorioEntero(0, cromosoma.length);
        do{
            i = al.aleatorioEntero(0, cromosoma.length);
        }while(!cromosoma[i]);
        cromosoma[i] = false;
        return calcularSuma(moc);
    }
    
    public boolean validarSoluionIdeal(){
        if(mochila.getSolucion().length != 0){            
            Boolean[] copy = mochila.getSolucion();
            for (int i = 0; i < cromosoma.length; i++) {
                if(!(copy[i].equals(cromosoma[i]))){
                    return false;
                }
            }
            return true;
        }        
        if(fitness == mochila.getK()){
            return true;
        }
        return false;
    }
  
    @Override
    public Individuo copy() {
        Object obj = null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("The class could not be cloned");
        }
        return (Individuo)obj;
    }
   
    @Override
    public double getFitness() {
        return this.fitness;
    }

    @Override
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }
    
    
}

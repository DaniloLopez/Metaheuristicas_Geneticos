package individuo;

import Operadores_Mochila.Articulo;
import Operadores_Mochila.Mochila;
import Utilidades.Aleatorio;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Individuo_mochilaPSO implements Cloneable {
    
    protected int[] cromosoma ;
    //el vector de velocidad es un vector que almacena la probabilidad de que el cromosoma permanezca sin cambio
    protected double[] velocidad;
    protected double fitness;        
    protected double probAddRuido;
    protected Mochila mochila ;    
    protected Individuo_mochilaPSO best;

    public Individuo_mochilaPSO(Mochila mochila, double prob) {
        this.mochila = mochila;
        this.probAddRuido = prob;
    }

    public Individuo_mochilaPSO(int[] vector, Mochila mochi, double prob) {
        cromosoma = vector;
        this.probAddRuido = prob;
        this.mochila = mochi;
    }
      
    public int[] getCromosoma() {
        return cromosoma;
    }
    
    public void setCromosoma(int[] cromosoma) {
        this.cromosoma = (int[])cromosoma;
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
            if(cromosoma[i] == 1){
                objAux =  moc.get(i).getValor() + objAux;
            }
        }
        return objAux;
    }
    
    public double cuadrarMochila(List<Articulo> moc){
        Aleatorio al = new Aleatorio(0);
        int i ;
        do{
            i = al.aleatorioEntero(0, cromosoma.length);
        }while(cromosoma[i] == 0);
        cromosoma[i] = 0;
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
      
    public Individuo_mochilaPSO copy() {
        Object obj = null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("The class could not be cloned");
            System.exit(0);
        }
        return (Individuo_mochilaPSO)obj;
    }
       
    public double getFitness() {
        return this.fitness;
    }
    
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }

    public double[] getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double[] velocidad) {
        this.velocidad = velocidad;
    }        

    public Individuo_mochilaPSO getBest() {
        return best;
    }

    public void setBest(Individuo_mochilaPSO best) {
        this.best = best;
    }        
}

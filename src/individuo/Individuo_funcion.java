/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individuo;

import java.util.Arrays;
import Utilidades.Aleatorio;
import funciones.Funcion;


/**
 *
 * @author Danilo 
 */
public class Individuo_funcion  implements Cloneable, Individuo{
    
    //varibale que almacenará el tipo de dato    
    private Double[] cromosoma;
    private double fitness;
    private Funcion funcion;    
    private double probabilitiAddNoise;
    private double halfRangeNoise;
    private double paso;
    
    Aleatorio random = new Aleatorio(0);
    
    public Individuo_funcion(Double[] cromosoma, Funcion funcion, double prob, double range, double paso) {
        this.cromosoma = cromosoma;
        this.funcion = funcion;        
        this.probabilitiAddNoise = prob;
        this.halfRangeNoise = range;        
        this.paso = paso;        
        //calcularFitness();
    }   

    public Individuo_funcion(Double[] vn, Individuo ind) {
        Individuo_funcion d = (Individuo_funcion) ind;
        this.cromosoma = vn;
        this.funcion = d.getFuncion();
        this.probabilitiAddNoise = d.getProbabilitiAddNoise();
        this.halfRangeNoise = d.getHalfRangeNoise();
        this.paso = d.getPaso();        
        //calcularFitness();
    }
    
    public void calcularFitness(){
        this.fitness = funcion.calculateFitness(this);
    }
        
    public Individuo tweak(Individuo ind, double min, double max) {
        double n;
        Individuo_funcion d = (Individuo_funcion)ind;
        Double vn[] = new Double[d.getCromosoma().length];
        int i = 0;
        
        for (Double dato : d.getCromosoma()) {
            //if probability > un numero aleatorio entre 0.0 y 1.0 
            if (random.nextAleatorio(1) <= d.getProbabilitiAddNoise()){
                do{
                    //encontrar numero aleatorio entre el rango de ruido
                    n = redondearDecimales(random.random(d),1);
                //mientras el valor se salga de los valores minimo y maximo, repita...
                }while(min > (dato + n)  || (dato + n)> max);
                //adicionar el paso y el numero aleatorio asl valor del vector de la posicion i
                vn[i] = redondearDecimales(dato + n, 1);                
                i++;
            }else{               
                vn[i] = dato;                               
                i++;
            }
        }        
        return new Individuo_funcion(vn, d);
    }  
    
//    public Individuo tweakGaussian(Individuo d, double min, double max) {
//        double n;
//        d = (Individuo_funcion)d;
//        Double vn[] = new Double[d.getCromosoma().length];
//        int i = 0;
//        for (Double dato : d.getCromosoma()) {
//            //if probability > un numero aleatorio entre 0.0 y 1.0 
//            if (d.getProbabilitiAddNoise() >= random.nextAleatorio(2)){
//                do{
//                    //encontrar numero aleatorio entre el rango de ruido
//                    n = redondearDecimales(random.random(d),1);
//                //mientras el valor se salga de los valores minimo y maximo, repita...
//                }while(min > (dato + n)  || (dato + n)> max);
//                //adicionar el paso y el numero aleatorio asl valor del vector de la posicion i
//                vn[i] = redondearDecimales(dato + n, 1);
//                i++;
//            }
//        }
//        return new Individuo(vn, d);
//    }  
    
    public double redondearDecimales(double valorInicial, int numeroDecimales){                     
        int n = 1;
        for (int i = 0; i < numeroDecimales; i++) {
            n = n*10;
        }        
        double rounded = (double) Math.round(valorInicial * n) / n;                     
        return rounded;
    }
     
    
    public double getProbabilitiAddNoise() {
        return probabilitiAddNoise;
    }

    public void setProbabilitiAddNoise(double probabilitiAddNoise) {
        this.probabilitiAddNoise = probabilitiAddNoise;
    }

    public double getHalfRangeNoise() {
        return halfRangeNoise;
    }

    public void setHalfRangeNoise(double halfRangeNoise) {
        this.halfRangeNoise = halfRangeNoise;
    }

    
    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    
    @Override
    public Double[] getCromosoma() {
        return cromosoma;
    }

    @Override
    public void setCromosoma(Object[] cromosoma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setCromosoma(Double[] cromosoma) {
        this.cromosoma = cromosoma;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(Double fitness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }           

    public double getPaso() {
        return paso;
    }

    public void setPaso(double paso) {
        this.paso = paso;
    }        
    
    /**
     *metodo implementado de la interfaz cloneable para poder clonar objetos de este tipo
     * @return una copia del objeto de esta clase     
     */   
    @Override
    public Individuo copy() {
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("The class could not be cloned");
        }
        return (Individuo)obj;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Arrays.hashCode(this.cromosoma);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Individuo other = (Individuo_funcion) obj;
        if (!Arrays.equals(this.cromosoma, (Double[])other.getCromosoma())) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {        
        return "TipoDato{" + "dato=" + Arrays.toString(cromosoma) + '}';        
    }
                        
    

    

    
        
    
}

package funciones;

import individuo.Individuo;

/**
 *
 * @author Danilo
 */
public abstract class Funcion {
    
    private double min;
    private double max;
    private String nombreFuncion;    

    public Funcion(double min, double max, String nombre) {
        this.min = min;
        this.max = max;
        this.nombreFuncion = nombre;        
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }            
    
    /**
     * evalua si encontro la solucion ideal al problema
     * @param ind dato enviado para ser comparado
     * @return verdadero si valida que la solucion es la correcta, falso si la solucion no es la ideal
     */
    public abstract boolean validateIdealSolution(Individuo ind);
    
    /**
     * calcular el fitness del vector
     * @param ind individuo a calcular el fitness
     * @return numero entero con el valor del fitness
     */
    public abstract double calculateFitness(Individuo ind);
    
}

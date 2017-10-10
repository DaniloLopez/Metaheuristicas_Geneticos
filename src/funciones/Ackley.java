/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import individuo.Individuo;
import individuo.Individuo_funcion;

/**
 *
 * @author Danilo
 */
public class Ackley extends Funcion {
    
    private double minEsperado = 0.1;
    private Double valorminimoEsperado = 0.1;

    public Ackley(int min, int max) {
        super(min, max, "ackley");
    }
      
    @Override
    public boolean validateIdealSolution(Individuo s) {
        //System.out.println("solucion: "+s.getFitness()+"  minEsperado:" + valorminimoEsperado);
        return (s.getFitness() <= valorminimoEsperado);
    }

    @Override
    public double calculateFitness(Individuo s) {
        try{                        
            Double datos[] = (Double[])((Individuo_funcion)s).getCromosoma();            
            int d = datos.length;
            double a = 20;
            double b = 0.2;                        
            double sum1 = -a * Math.exp(-b * Math.sqrt(sumDimDim(datos) / d));
            double sum2 = -Math.exp(sumDim2(datos) / d);
            
            return (sum1 + sum2 + a + Math.exp(1));
        }catch(NullPointerException e){
            System.out.println("error calculando fitnes. Clase Ackley.java - linea 37");
        }
        return 0;
    }
    
    /**
     * calcula la suma de las n dimensiones al cuadrado
     * @param datos
     * @return 
     */
    public double sumDimDim(Double[] datos){
        double sum = 0;
        for (Double dato : datos) {
            sum += dato*dato;
        }
        return sum;
    }
    
    /**
     * calcula la suma de las n dimensiones evaluadas en COS(2 * PI * xi)
     * @param datos
     * @return 
     */
    public double sumDim2(Double[] datos){
        double sum = 0;
        double c = 2 * Math.PI;
        for (Double dato : datos) {            
            sum += Math.cos(c * dato);
        }
        return sum;
    }
}

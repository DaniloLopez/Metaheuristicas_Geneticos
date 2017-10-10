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
public class Griewank extends Funcion{
        
    private Double valorminimoEsperado = 0.1;

    public Griewank(int min, int max) {
        super(min, max, "griewank");
    }

    @Override
    public boolean validateIdealSolution(Individuo s) {        
        return (s.getFitness() <= valorminimoEsperado);
    }

    @Override
    public double calculateFitness(Individuo s) {                      
        double sum = 0;
        double prod = 1;
        int tam = ((Individuo_funcion)s).getCromosoma().length;
        for (int i= 0;  i < tam; i++) {
            double crom  = (double)((Individuo_funcion)s).getCromosoma()[i];
            sum = sum + Math.pow(crom, 2)/4000;
            prod = prod * Math.cos(crom / Math.sqrt(i+1));
        }
        
        return (sum - prod + 1);
    }

    
    
}

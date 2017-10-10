package funciones;

import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Danilo
 */
public class Rastrigin extends Funcion{

    private double minEsperado = 0.1;
    private Double valorminimoEsperado = 0.1;
    
    public Rastrigin(double min, double max) {
        super(min, max, "Rastrigin");
    }

    @Override
    public boolean validateIdealSolution(Individuo s) {
        return (s.getFitness() <= valorminimoEsperado);
    }

    @Override
    public double calculateFitness(Individuo s) {
        try{            
            Double datos[] = (Double[])((Individuo_funcion)s).getCromosoma();
            double suma = 0;            
            for (Double dato : datos) {
                suma += (dato*dato) - (10*Math.cos(2*Math.PI*dato)) + 10;
            }
            return suma;
        }catch(NullPointerException e){
            System.out.println("error calculando fitnes");
        }
        return 0;
    }
    
}

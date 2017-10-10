package funciones;

import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.Arrays;

/**
 *
 * @author Danilo
 */
public class Step extends Funcion{
    
    private double minEsperado = 0.1;
    private Double valorminimoEsperado = 0.1;
    double radio;

    public Step(int min, int max, double radio) {
        super(min, max, "step");
        this.radio = radio;
    }   

    @Override
    public boolean validateIdealSolution(Individuo s) {
        //System.out.println("solucion: "+ Arrays.toString(s.getCromosoma())+ "  fit: "+s.getFitness()+"  minEsperado:" + valorminimoEsperado);
        return (s.getFitness() <= valorminimoEsperado);
    }

    @Override
    public double calculateFitness(Individuo s) {
        try{            
            //System.out.println(Arrays.toString(((Individuo_funcion)s).getCromosoma()));
            Double datos[] = (Double[])((Individuo_funcion)s).getCromosoma();
            double suma = 0;
            for (double dato : datos) {
                suma += (dato + 0.5)*(dato + 0.5);
//                System.out.print(suma + "   ");
            }
            return suma;
        }catch(NullPointerException e){
            //System.out.println("error calculando fitnes");
        }
        return 0;
    }
    
}

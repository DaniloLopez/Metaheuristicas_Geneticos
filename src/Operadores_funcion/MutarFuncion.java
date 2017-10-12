package Operadores_funcion;

import Interfaces_Operador_Genetico.OperadorMutacion;
import Utilidades.Aleatorio;
import individuo.Individuo;
import individuo.Individuo_funcion;
import java.util.ArrayList;

/**
 *
 * @author Unicauca
 */
public class MutarFuncion implements OperadorMutacion{

    Aleatorio al = new Aleatorio(0);
    
    @Override
    public ArrayList<Individuo> mutar(ArrayList<Individuo> pob_individuo, double probabilidad, int varianza) {        
        for (Individuo ind : pob_individuo) {            
            Double[] aux = (Double[])((Individuo_funcion)ind).getCromosoma().clone();
            Double[] mutacion = gaussiana(aux, probabilidad, ((Individuo_funcion)ind).getFuncion().getMin(), ((Individuo_funcion)ind).getFuncion().getMax());
            
            ((Individuo_funcion)ind).setCromosoma(mutacion);
            
        }      
        return pob_individuo;
    }

    private Double[] gaussiana(Double[] v, double p, double min, double max) {
        double rnd;
        double n = 0;
        double o2 = calcularVarianza2(v);
        for (int i = 0; i < v.length; i++) {
            rnd = al.nextAleatorio(3);
            if(rnd < p){
                do{
                    n = distribucionNormal(o2, 0);
                }while(min > n || n > max);
                v[i] = n;
            }            
        }
        return v;
    }
    
    /**
     * convierte la distribucion normal de java N(0,1) a N(u,o2)     
     * @param o2 variance
     * @param u mean
     * @return 
     */
    public double distribucionNormal(double o2, int u){ //funcion de densidad de probabilidad normal       
       //double dn = (1/Math.sqrt(2* Math.PI * o2)) * Math.exp(-((x-u)*(x-u))/(2*o2));
       double dn = u + (Math.sqrt(o2)*al.nextAleatorio(3));
       return dn;
    }
        
    
    public double calcularVarianza2(Double[] datos){
        double suma = 0;
        for (Double dato : datos) {
            suma += dato;
        }
        double u = suma/datos.length;        
        double total = 0;
        for (Double dato : datos) {
            total += (dato - u)*(dato - u);
        }
        return total / datos.length;        
    }
    
    

}

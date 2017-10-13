/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheuristicas_geneticos;

/**
 *
 * @author Danilo
 */
public class Metaheuristicas_Geneticos {
            
    //probabilidad de adicionar ruido
    public static double  prob = 0.05;        
    //amplitud para calcular aleatorio
    public static double  radio = 0.9;
    public static int     paso    = 1;      
    
   /**
     * @param args the command line arguments
     */       
    public static void main(String[] args) {                                     
        
        //correr la implementacion para algoritmos geneticos orientado al problema de optimizacion de funciones
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Main_Funciones mf = new Main_Funciones();        
        //mf.geneticoFuncion();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        
        //correr la implementacion para algoritmos geneticos orientado al problema de la mochila
        //======================================================================                
        Main_Mochila mm = new Main_Mochila();
        mm.geneticoMochila();
        //======================================================================
        
    }//fin clase main                          
}



/*
//<editor-fold defaultstate="collapsed" desc="hill climbing">
        //leer datos del archivo de verificacion
        //LeerArchivoPrueba lap = new LeerArchivoPrueba();        
        //datos = lap.leerArchivo("2017-09-13-Proyecto-datos-regresion.txt");      
        //instancia de la clase Regresion
        //Regresion regPol  = new Regresion(min, max, datos);
        //algoritmos.add(new HillClimbing(regPol));                
                
        //vector de resultados        
        ArrayList<ResultadoIndividuo> res = new ArrayList();                
        ResultadoFuncion resFun;
        
        System.out.println("Calculando...");
        for (HillClimbingAbstract algHC : algoritmos) {                    
            for (int i = 0; i < cantPruebas; i++) {
                random = new Aleatorio(i);
                //generar vector  de aleatorios
                Double vectorAleatorios[] = random.vectorAleatorioDecimalProb(
                        algHC.getFuncion().getMin(), algHC.getFuncion().getMax(), 
                        dimensiones, 0.7, 1
                );
                
                double start = System.currentTimeMillis();
                
                //iniciar algoritmo hill climbing
                resFun = algHC.inicio(new Individuo_funcion(vectorAleatorios, algHC.getFuncion(), prob, radio, paso),EFOs);                
                
                double end = System.currentTimeMillis() - start;
                
                res.add(new ResultadoIndividuo( algHC.getNonmbreFuncion(),algHC.getClass().getSimpleName(), 
                                                resFun.getIteracion(), end, resFun.getResultado()));                
                
                System.out.println("+++++  Iteracion " + i + " res " + resFun.getResultado().toString() );                
            }
            objEstadistica.calcularEstadisticas(res, algHC, dimensiones, resFinal);
            //res.clear();
            System.out.println("********encontrado:  alg -> "+algHC.getClass().getSimpleName());
        }
        
        
        //escribir el resultado en el archivo
        boolean estado = objEscribirArchivo.excribirResultados(nombreArchivo, resFinal);                           
        if(estado){
            System.out.println("Programa finalizado.");
        }else{
            System.out.println("No se completo la escritura de resultados en el archivo.");
        }
        objEscribirArchivo.excribirSolucion(nombreArchivo, res);
//</editor-fold>
*/
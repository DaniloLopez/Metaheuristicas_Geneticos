package metaheuristicas_geneticos;

import Archivo.LeerArchivoMochila;
import Operadores_Mochila.Mochila;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Main_Mochila {
         
    /**
    * implementacion de algoritmo genetico para el problema de la mochila
    */
    public void geneticoMochila(){
        String nombreArchivo = "f1.txt";
        LeerArchivoMochila lam = new LeerArchivoMochila();
        Mochila moc = lam.leerArchivo(nombreArchivo);
        
        
        System.out.println(moc.toString());
    }
}

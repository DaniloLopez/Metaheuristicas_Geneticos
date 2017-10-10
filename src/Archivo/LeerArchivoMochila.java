package Archivo;

import Archivo.Archivo;
import Operadores_Mochila.Mochila;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class LeerArchivoMochila {
    Archivo file;
    Mochila mochila;

    public LeerArchivoMochila() {
        file = new Archivo();
        
    }    
    
    public Mochila leerArchivo(String ruta){                
        mochila = new Mochila();
        String linea = "";
        file.abrirArchivo(ruta, false, false);
        crearMochila(file.leerArchivo());        
        while(file.puedeLeer()){
            linea = file.leerArchivo();
            adicionarParametros(linea);
        }
        file.cerrarArchivo();
        return mochila;
    }
    
    
    private void crearMochila(String linea) {
        try{
            linea = linea.trim();
            String[] valores = linea.split(" ");
            mochila.setN(Double.parseDouble(valores[0]));
            mochila.setK(Double.parseDouble(valores[1]));
        }catch(Exception e){
            System.out.println("error en metodo crearMochila de clase LeerArchivoMochila. " + e.getMessage());
        }
    }
      
    private boolean adicionarParametros(String linea){
        try {
            linea=linea.trim();
            String[] valores = linea.split(" ");
            switch(valores.length){
                case 1:
                    adicionarOBJ(valores);
                    return true;                    
                case 2:
                    return adicionarArticulo(valores);                    
                default:
                    adicionarSolucion(valores);
                    return true;                    
            }                                                   
        } catch (Exception e) {
            System.out.println("error en el metodo adicionarArticulo() de la clase LeerArchivoMochila");
            return false;
        }
        
    }               

    private void adicionarOBJ(String[] valores) {
        mochila.setObj(Double.parseDouble(valores[0]));
    }

    private void adicionarSolucion(String[] valores) {
        int tam = valores.length;
        Boolean[] datos = new Boolean[tam];
        for (int i= 0; i < tam; i++){
            if(valores[i].equals("1")){
                datos[i] = true;
            }else{
                datos[i] = false;
            }
        }            
        mochila.setSolucion(datos);
    }
    

    private boolean adicionarArticulo(String[] valores) {
        return mochila.addArticulo(Double.parseDouble(valores[0]), Double.parseDouble(valores[1]));
    }

  
     
}

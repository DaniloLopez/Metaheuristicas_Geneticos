package Archivo;

import Operadores_Mochila.Mochila;

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
        String linea;
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
                    //adicionar objetivo                    
                    mochila.setObj(Double.parseDouble(valores[0].replace(',', '.')));
                    return true;
                case 2:
                    //adicionar articulo
                    return mochila.addArticulo(Double.parseDouble(valores[0].replace(',', '.')), Double.parseDouble(valores[1].replace(',', '.')));
                default:
                    adicionarSolucion(valores);                    
                    return true;
            }                      
        } catch (Exception e) {
            System.out.println("error en el metodo adicionarArticulo() de la clase LeerArchivoMochila");
            return false;
        }
        
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
}
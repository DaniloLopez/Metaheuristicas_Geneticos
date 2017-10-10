package Operadores_Mochila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Unicauca
 */
public class Mochila {
    
    double n; //numero de item
    double k; //capacidad
    double obj;
    Boolean solucion[];
    List<Articulo> articulos;

    /**
     * constructor para inicializar los parametros n, k  y la ista de articulos
     * @param n numero de items
     * @param k capacidad de la mochila
     * @param obj valor de los objetos contenidos segun el vector de solucion
     * @param articulos lista de articulos
     * @param solucion vector que contiene la solucion
     */
    public Mochila(double n, double k, double obj, List<Articulo> articulos, Boolean[] solucion) {
        this.n = n;
        this.k = k;
        this.obj = obj;
        this.articulos = articulos;
        this.solucion = solucion;
    }
    
    /**
     * constructor para inicializar los parametros n y k. solucion = [], articulos = ()
     * @param n numero de items
     * @param k capacidad de la mochila     
     */
    public Mochila(double n, double k) {
        this.n = n;
        this.k = k;
        this.obj = 0;        
        this.articulos = new ArrayList<>();
    }

    /**
     * constructor por defecto. n = 0 , k = 0, obj = 0, solucion = [], articulos = ()
     */
    public Mochila() {
        this.n = 0;
        this.k = 0;
        this.obj = 0;
        this.solucion = new Boolean[0];
        this.articulos = new ArrayList<>();
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public double getObj() {
        return obj;
    }

    public void setObj(double obj) {
        this.obj = obj;
    }

    public Boolean[] getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean[] solucion) {
        this.solucion = solucion;
    }
        
    /**
     * adicionar un nuevo articulo al la lista de articulos
     * @param valor valor del articulo
     * @param peso peso del articulo
     * @return True si se pudo agregar el artículo, en caso contrario False
     */
    public boolean addArticulo(double valor, double peso){
        try{
            return articulos.add(new Articulo(valor, peso));
        }catch(Exception e){
            System.out.println("ocurrio un error almacenando el articulo. clase Mochila, metodo addArticulo()");
            return false;
        }
    }
    
    /**
     * remover un artículo del a mochila
     * @param articulo articulo que se desea remover de la lista
     * @return True si se elimino el articulo del la lista, en caso contrario False
     */
    public boolean removeArticulo(Articulo articulo){
        try{
            return articulos.remove(articulo);
        }catch(Exception e){
            System.out.println("error eliminando el articulo. clase mochila, metodo removeArticulo()");
            return false;
        }
            
    }

    @Override
    public String toString() {
        return "Mochila{" + "n=" + n + ", k=" + k + ", obj=" + obj + ", solucion=" + Arrays.toString(solucion) + ", articulos=" + articulos + '}';
    }

    
}

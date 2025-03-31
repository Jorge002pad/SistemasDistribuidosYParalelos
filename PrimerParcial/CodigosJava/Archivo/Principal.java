package Archivo;
//Programa que ejemplifica la extracción de datos de un archivo de texto para manipularlos en un stream

/*Contenido del archivo datos.txt
  1,pc,1000
  2,mac,2000
  3,android,300
  4,ios,900
  5,pc,300
 
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
 

 class Computadora{
 
    private int numero;
    private String sistema;
    private double precio;
   
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getSistema() {
        return sistema;
    }
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Computadora(int numero, String sistema, double precio) {
        super();
        this.numero = numero;
        this.sistema = sistema;
        this.precio = precio;
    }
    public static Computadora construyeDesdeArray(String[] elementos) {
         //Cambia de texto a objetos del tipo de objeto requerido
        return new Computadora (Integer.parseInt(elementos[0]),elementos[1],Double.parseDouble(elementos[2]));
         
    }
}


public class Principal {
 
    public static void main(String[] args) {
 
        String archivo = "datos.txt"; //uso de: \\ para subdirectorios
 
 
     /* de usar el método lines de la clase Files para construir un Stream.
     Este Stream nos permite leer linea a linea el fichero de texto.
     */
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
        /*  Una vez que se accede a cada linea, se realizan dos operaciones de mapeo, se convertimos cada linea en un objeto
        y posteriormente  en un número.
        */
             stream.map(linea -> linea.split(","))
                   .map(Computadora::construyeDesdeArray)  
                  .mapToDouble(o -> o.getPrecio())   //Otros métodos mapToInt(), mapToLong(), mapToDouble()
                     
                  .onClose(() -> System.out.println("\nFinalizando"))
                 
                  // Se realiza una reducción y se suman todos los elementos.
                  .reduce(Double::sum)    //Double::sum es una función que se utiliza para sumar dos números de punto flotante.
                  .ifPresent(System.out::println); //ifPresent es un método de la clase Optional
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}

/* Path es un objeto que contiene la información para especificar la ubicación de un archivo o directorio
se encuentra en el paquete  java.nio.file

Otros métodos relacionados con rutas:
getFileName() devuelve el nombre del archivo de la ruta.
getParent() devuelve el directorio "principal" de la ruta actual.
getRoot() devuelve el directorio "raíz".


.map()  sirve para convertir o transformar datos de un tipo a otro
Es una operación intermedia perezoza(lazyness)
Se utilizan cuando se sabe que los resultados son de tipos int, long o double


.reduce() se utiliza para realizar operaciones de reducción en los elementos de un flujo.

.ifPresent() se utiliza para ejecutar una acción si un objeto Optional contiene un valor no nulo, y mostrar ese valor en la pantalla.
*/
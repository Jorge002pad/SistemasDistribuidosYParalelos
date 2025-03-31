package BateriasArchivo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream; 

class Dispositivo{
    String Marca;
    String Modelo;
    int Bateria;

    public Dispositivo(){
        Marca = "Iphone";
        Modelo = "15 Pro";
        Bateria = 60;
    }
    public Dispositivo(int bateria,String marca, String modelo){
        Marca = marca;
        Modelo = modelo;
        Bateria = bateria;
    }
    public String get_Marca(){
        return Marca;
    }
    public String get_Modelo(){
        return Modelo;
    } 
    public int get_Bateria(){
        return Bateria;
    }
    public void set_Marca(String marca){
        Marca = marca;
    }
    public void set_Modelo(String modelo){
        Modelo = modelo;
    } 
    public void set_Bateria(int bateria){
        Bateria = bateria;
    } 
    public static Dispositivo construyeDesdeArray(String[] elementos) {
         //Cambia de texto a objetos del tipo de objeto requerido
        return new Dispositivo (Integer.parseInt(elementos[2]),elementos[0],elementos[1]);
         
    }  
    
}



public class Main {
    public static void main(String[] args) {
        String archivo = "datos.txt"; //uso de: \\ para subdirectorios

       try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
        /*  Una vez que se accede a cada linea, se realizan dos operaciones de mapeo, se convertimos cada linea en un objeto
        y posteriormente  en un número.
        */
             stream.map(linea -> linea.split(","))
                   .map(Dispositivo::construyeDesdeArray)  
                  .mapToInt(o -> o.get_Bateria())   //Otros métodos mapToInt(), mapToLong(), mapToDouble()
                     
                  .onClose(() -> System.out.println("\nFinalizando"))
                 
                  // Se realiza una reducción y se suman todos los elementos.
                  .reduce(Integer::sum)    //Double::sum es una función que se utiliza para sumar dos números de punto flotante.
                  .ifPresent(System.out::println); //ifPresent es un método de la clase Optional
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}

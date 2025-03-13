package Dispositivo;
import java.util.*;

class Dispositivo{
    String Marca;
    String Modelo;
    int Bateria;

    public Dispositivo(){
        Marca = "Iphone";
        Modelo = "15 Pro";
        Bateria = 60;
    }
    public Dispositivo(String marca,String modelo,int bateria){
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

}

public class Main {
   public static void main(String[] args) {
    ArrayList<Dispositivo> lista = new ArrayList<Dispositivo>();
    int suma=0;

    // Agregar más dispositivos a la lista
    lista.add(new Dispositivo("Apple", "iPhone 14", 75));
    lista.add(new Dispositivo("Samsung", "Galaxy Z Fold 5", 65));
    lista.add(new Dispositivo("Google", "Pixel 8 Pro", 80));
    lista.add(new Dispositivo("Xiaomi", "Mi 11 Ultra", 55));
    lista.add(new Dispositivo("OnePlus", "11 Pro", 90));
    lista.add(new Dispositivo("Sony", "Xperia 1 V", 60));
    lista.add(new Dispositivo("Motorola", "Edge 40", 70));
    lista.add(new Dispositivo("Huawei", "P50 Pro", 50));
    lista.add(new Dispositivo("Realme", "GT Neo 5", 85));
    lista.add(new Dispositivo("Asus", "ROG Phone 7", 95));


    //Muestra el contenido de la lista con iterator
    for(Dispositivo ejemplo : lista){
        suma = suma + ejemplo.get_Bateria(); 
    }

    System.out.println("El promedio de carga de la bateria de todas los dispositivos es: " + (suma/lista.size()) + "%");

   }
}

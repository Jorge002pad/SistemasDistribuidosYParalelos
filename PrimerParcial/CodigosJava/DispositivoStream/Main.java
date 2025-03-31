package DispositivoStream;
import java.util.*;
import java.util.stream.Stream;

class Dispositivo{
    String Marca;
    String Modelo;
    int Bateria;
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
    Stream <Dispositivo> prueba = Stream.of(new Dispositivo("Apple", "iPhone 14", 75), 
                                                        new Dispositivo("Samsung", "Galaxy Z Fold 5", 65),
                                                        new Dispositivo("Google", "Pixel 8 Pro", 80),
                                                        new Dispositivo("Xiaomi", "Mi 11 Ultra", 55),
                                                        new Dispositivo("OnePlus", "11 Pro", 90),
                                                        new Dispositivo("Sony", "Xperia 1 V", 60),
                                                        new Dispositivo("Motorola", "Edge 40", 70),
                                                        new Dispositivo("Huawei", "P50 Pro", 50),
                                                        new Dispositivo("Realme", "GT Neo 5", 85),
                                                        new Dispositivo("Asus", "ROG Phone 7", 95));

    //mostrar las baterias cuyo nombre terminan con la letra "g"
   //Stream <Dispositivo> terminacion = prueba.filter()


   //Mostrar las baterias con porcentaje mayor al promedio
   long count = prueba.count();
   System.out.println("el porcentaje de la bateria es: " + count);


   }

   


}
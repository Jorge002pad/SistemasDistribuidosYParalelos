import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class Usuario{
    int Año;

    public Usuario(int año){
        this.Año=año;
    }
    public int getAño(){
        return Año;
    }

}


public class Myclass{
    public static void main(String[] args) {
        List <Usuario> usuarios = Arrays.asList(new Usuario(2000),
                                                new Usuario(1999),
                                                new Usuario(2015),
                                                new Usuario(1980));

        List<Usuario> usuarioAño = usuarios.stream()
                                    .filter(usu-> usu.getAño() >=2000)
                                    .collect(Collectors.toList());      //para convertir a una lista

        System.out.println("Usuarios con año de creacion mayor o igual a 2000: " + usuarioAño);


        Stream<Usuario> usuarios2 = Stream.of(new Usuario(2000),
                                                new Usuario(1999),
                                                new Usuario(2015),
                                                new Usuario(1980));

        Stream <Usuario> usuariosFor = usuarios2.filter(usu-> usu.getAño() >=2000);
        usuariosFor.forEach(System.out::println);
    }
}
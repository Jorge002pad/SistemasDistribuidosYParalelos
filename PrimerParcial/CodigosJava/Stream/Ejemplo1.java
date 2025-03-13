package Stream;
import java.util.*;

public class Ejemplo1 {
    public static void main(String[] args) {
        var ciudades  = List.of("CDMX", "Oaxaca", "Guadalajara", "Cancun");
        ciudades.
        stream()
        .filter(e->e.endsWith("a"))
        .forEach(System.out::println);
    }
}

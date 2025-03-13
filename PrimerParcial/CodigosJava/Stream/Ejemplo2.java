package Stream;

import java.util.Arrays;
import java.util.List;

public class Ejemplo2 {
    public static void main(String[] args) {
        List<Integer>lista = Arrays.asList(1,1,2,2,3,4,4,5,5);
        lista.stream().distinct().forEach(System.out::println);
    }
    
}

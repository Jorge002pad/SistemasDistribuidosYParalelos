package Stream;

import java.util.*;

public class Ejemplo4 {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for(int x=0; x<100; x++) integers.add(x);

        integers.stream()
        .filter(val -> val%2==0)
        .forEach(item->System.out.println(item));
    }
    
}

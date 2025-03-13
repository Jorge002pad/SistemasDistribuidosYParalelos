package Stream;

import java.util.Arrays;
import java.util.List;

//Filtrado coincidencia con un dato

public class Ejemplo3 {
    public static void main(String[] args) {
        List<String> datos= Arrays.asList("uno", "dos", "tres", "cuatro", "cinco", "seis", "tres");

        datos.stream()
        .filter(unDato -> unDato == "tres")
        .forEach(datoFiltrado->System.out.println(datoFiltrado));

    }
    
}

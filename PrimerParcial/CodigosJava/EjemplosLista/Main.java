package EjemplosLista;
import java.util.*; 

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        list.add("Elemento 1");
        list.add("Elemento 2");
        list.add(2,"Elemento 3"); //Se coloca el Elemento 3 en la posicion 2
        list.add("Elemento 4");

        System.out.println("la lista tiene los siguientes elementos:" + list);


        //Muestra el contenido de la lista con iterator
        Iterator<String> itr = list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        //Borra los elementos de la lista
        list.clear();
        System.out.println("La lista tiene los siguientes elementos despues de clear(): " + list);

        //Se vuelve a llenar la lista
        list.add("Elemento 1");
        list.add("Elemento 2");
        list.add(2,"Elemento 3"); //Se coloca el Elemento 3 en la posicion 2
        list.add("Elemento 4");
        list.add("Elemento 2");

        //Se obtiene el ultimo indice de los elementos dados
        int ultimo = list.lastIndexOf("Elemento 2");
        System.out.println("El valor indice de Elememto 2 es: " + ultimo);

        //Se clona un objeto list
        Object listClone = list.clone();
        System.out.println("Objeto Clonado: " + listClone);

        //Se obtiene el indice de un elemento
        int pos = list.indexOf("Elemento 2");
        System.out.println("El indice de Elememto 2 es: " + pos);

        //Verifica si el arreglo esta vacio
        boolean check = list.isEmpty();
        System.out.println("¿Esta vacio el Arreglo? " + check);

        //Se obtiene el tamaño del arreglo
        int size = list.size();
        System.out.println("El tamaño de list es: " + size);

        //Se verifica si un elemento esta en la lista
        boolean element = list.contains("Elemento 5");
        System.out.println("Verifica si un elemento esta en la lista, Elemento 5: " + element);

        //Se obtiene un elemento dada una posicion
        String item = list.get(0);
        System.out.println("El elemento en el indice 0 es: " + item);

        //Se obtienen los elementos de un arreglo
            //Forma 1 Obtener el tamaño de la lista
        System.out.println("Se obtienen los elementos con un ciclo usando un indice y el tamaño de la lista");
        for (int i=0; i<list.size(); i++){
            System.out.println("Indice: " + i + "Elemento: " + list.get(i));
        }

            //Forma 2 Obtener los elementos en un ciclo
        System.out.println("Obteniendo los elementos con un ciclo");
        for(String str:list){
            System.out.println("El elemento es: " + str);
        }

            //Forma 3 Usando iterator
        System.out.println("Obteniendo los elementos con Iterator");
        for (Iterator<String> it = list.iterator(); it.hasNext();){
            System.out.println("Item es: " + it.next());
        }
    }   
}

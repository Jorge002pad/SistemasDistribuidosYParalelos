/* Ejemplo de atención a cajas con hilos
 *
 **/

 class CajeraThread extends Thread {

    private String nombre;

    private Cliente cliente;

    private long initialTime;

    CajeraThread(String nombre, Cliente cliente, long initialTime ){
        this.nombre=nombre;
        this.cliente=cliente;
        this.initialTime=initialTime;
       
    }
   
    public String getNombre(){
        return nombre;
    }
   
    public Cliente getCliente(){
        return cliente;
    }
   
    public long getInitialTime(){
        return initialTime;
    }

   
    public void run() {

        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                    + this.cliente.getNombre() + " EN EL TIEMPO: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000
                    + "seg");

        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1)
            + " del cliente " + this.cliente.getNombre() + "->Tiempo: "
            + (System.currentTimeMillis() - this.initialTime) / 1000
            + "seg");
        }

        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR "
                        + this.cliente.getNombre() + " EN EL TIEMPO: "
                        + (System.currentTimeMillis() - this.initialTime) / 1000
                        + "seg");
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}



 class Cliente {

    private String nombre;
    private int[] carroCompra;


    Cliente(String nombre,int carroCompra[] ){
        this.nombre=nombre;
        this.carroCompra=carroCompra;
    }
   
    public String getNombre(){
        return nombre;
    }

   public int[] getCarroCompra(){
       return carroCompra;
   }
}


public class TestCajasHilo {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
       
        // Tiempo inicial
        long initialTime = System.currentTimeMillis();
           
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1,initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2,initialTime);

        cajera1.start();
        cajera2.start();
    }
}
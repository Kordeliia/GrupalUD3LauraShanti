package Modelo;
import java.util.ArrayList;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class PedidoDAO {
	
    public static void insertarPedidoBBDD(Pedido pedido) {
    	SessionFactory mf = Modelo.ManejoDeSesion.getmf();
        Session ms = mf.openSession();
        try {
            System.out.println("Un momento, estamos iniciando la conexión con la Base de Datos para introducir los datos a la tabla de pedidos");
            ms.beginTransaction();
            System.out.println("Estamos enviando sus datos al servidor de la base de datos.");
            ms.save(pedido);
            ms.getTransaction().commit();
            System.out.println("Se ha introducido a la BBDD el nuevo pedido con éxito");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Pedido> mostrarPedidos() {
        try {
            SessionFactory mf = ManejoDeSesion.getmf();
            Session ms = mf.openSession();
            //Obtenemos todos los registros de la tabla
            ms.beginTransaction();
            Query query = ms.createQuery("FROM Pedido");
            ArrayList<Pedido> olpedido = (ArrayList<Pedido>) query.list();
            ms.getTransaction().commit();
            return olpedido;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void actualizarPedidoBBDD(Pedido pedido) {
   	 SessionFactory mf = Modelo.ManejoDeSesion.getmf();
        Session ms = mf.openSession();
       try {
           System.out.println("Un momento, estamos iniciando la conexión con la Base de Datos para introducir los datos a la tabla de pedidos");
           ms.beginTransaction();
           System.out.println("Estamos enviando sus datos al servidor de la base de datos.");
           ms.update(pedido);
           ms.getTransaction().commit();
           System.out.println("Se ha introducido a la BBDD el nuevo pedido con éxito");
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
              

        public static void borrarPedidoBBDD(Pedido pedido) {
          	 SessionFactory mf = Modelo.ManejoDeSesion.getmf();
               Session ms = mf.openSession();
          
       
           try {
               System.out.println("Un momento, estamos iniciando la conexión con la Base de Datos para proceder a su lectura");
               ms.beginTransaction();
               ms.delete(pedido);
               System.out.println("El pedido ha sido borrado de la BBDD");
               ms.getTransaction().commit();
           } catch (Exception e) {
               e.printStackTrace();
           } 
           
           
           } 

}


package t1_r3_databasemetadata;

import com.mysql.cj.jdbc.DatabaseMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Iván Zambrana Naranjo
 */
public class Ejercicio5 {

    public static void main(String[] args) {
            
            
        //Creamos escaner y declaramos variables para el menu
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        
        //Variables útiles para el menú
        
        PreparedStatement insercion = null;
        Statement exConsulta = null;
        ResultSet resultado = null;
        int option, row;
        
        try
        {
            
            //Pedimos pautas necesarias para la conexión a la base de datos
            System.out.println("inserta sgbd: ");
            String sgdb = sc.nextLine();
            System.out.println("inserta servidor: ");
            String server = sc.nextLine();
            System.out.println("inserta base de datos: ");
            String db = sc.nextLine();
            System.out.println("inserta usuario: ");
            String user = sc.nextLine();
            System.out.println("inserta contraseña: ");
            String passw = sc.nextLine();

           //Realizamos la conexión
           Connection conexion = null;
           Conectar con = new Conectar(sgdb, server, db, user, passw);
           conexion = con.getConnection();
            
           
            //Creacion del menu
            while(!exit) {
                //Mostrar opciones
                System.out.println("\n\n\n----------------------------------------");
                System.out.println("---------MENU SQL (METADATOS)-----------");
                System.out.println("----------------------------------------");
                System.out.println("1. Mostrar datos generales del SGBD y base de datos.");
                System.out.println("2. Mostrar datos de todas las tablas.");
                System.out.println("3. Mostrar datos de una tabla.");
                System.out.println("4. Mostrar columnas de una tabla.");
                System.out.println("5. Mostrar claves primarias de una tabla.");
                System.out.println("6. Mostrar las claves externas asociadas a la clave primaria de una tabla.");
                System.out.println("7. Mostrar procedimientos almacenados de la base de datos.");
                System.out.println("8. Salir.");

                //Captura opcion insertada por teclado
                System.out.println("Elija una opcion: ");
                option = sc.nextInt();

                //Manejo de opciones
                switch(option){
                    case 1:
                        System.out.println("-MOSTRAR DATOS DEL SGDB Y BASE DE DATOS-");
                        java.sql.DatabaseMetaData datos = conexion.getMetaData();
                        String nombre = datos.getDatabaseProductName();
                        String driver = datos.getDriverName();
                        String url = datos.getURL();
                        String usuario = datos.getUserName();
                        
                        System.out.println("NOMBRE: " + nombre);
                        System.out.println("DRIVER: " + driver);
                        System.out.println("URL: " + url);
                        System.out.println("USUARIO: " + usuario);
                        System.out.println("------------------------------");
                        break;


                    case 2:
                        System.out.println("-MOSTRAR DATOS DE TODAS LAS TABLAS-");


                        System.out.println("------------------------------");
                        break;
                    case 3:
                        System.out.println("-MOSTRAR DATOS DE UNA TABLA-");


                        System.out.println("------------------------------");
                        break;
                    case 4:
                        System.out.println("-MOSTRAR COLUMNAS DE UNA TABLA-");


                        System.out.println("------------------------------");
                        break;
                    case 5:
                        System.out.println("-MOSTRAR CLAVES PRIMARIAS DE UNA TABLA-");


                        System.out.println("------------------------------");
                        break;
                    case 6:
                        System.out.println("-MOSTRAR CLAVES EXTERNAS ASOCIADAS A LA CLAVE PRIMARIA DE UNA TABLA-");


                        System.out.println("------------------------------");
                        break;
                    case 7:
                        System.out.println("-MOSTRAR PROCEDIMIENTOS ALMACENADOS-");


                        System.out.println("------------------------------");
                        break;
                    case 8:    
                        System.out.println("Saliendo...");
                        exit=true;
                        break;
                    default:
                        System.out.println("Elija un numero entre 1 y 8.");

                }
            }
        } catch (ClassNotFoundException | SQLException cnfsql) {cnfsql.printStackTrace();}
    }
    
}

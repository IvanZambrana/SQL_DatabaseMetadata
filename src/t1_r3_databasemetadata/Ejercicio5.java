
package t1_r3_databasemetadata;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
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
        String nombreTabla;
        PreparedStatement insercion = null;
        Statement exConsulta = null;
        ResultSet resultado = null;
        ResultSet result = null;
        
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
           java.sql.DatabaseMetaData datos = conexion.getMetaData();
           
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
                        //Mostrar datos del sgdb y base de datos
                        System.out.println("-MOSTRAR DATOS DEL SGDB Y BASE DE DATOS-");
                        
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
                        resultado = datos.getTables(db, null, "%", null);
                        
                        
                        while(resultado.next())
                        {
                            System.out.println("Catalogo: " + resultado.getString("TABLE_CAT") + " | Esquema: " + resultado.getString("TABLE_SCHEM") + " | Nombre de la tabla: " + resultado.getString("TABLE_NAME") + " | Tipo: " + resultado.getString("TABLE_TYPE"));            
                        }
                        
                        System.out.println("------------------------------");
                        break;

                    case 3:
                        System.out.println("-MOSTRAR DATOS DE UNA TABLA-");
                        //Pedimos nombre de tabla
                        System.out.println("Inserte nombre de la tabla: ");
                        sc.nextLine();
                        nombreTabla = sc.nextLine(); 
                        
                        //java.sql.ResultSetMetaData rsmd = resultado.getMetaData();
                        //Comprobamos que no sea nula
                        if(!nombreTabla.equals(""))
                        {
                            resultado = datos.getTables(db, null, nombreTabla, null);
                            //Comprobamos que existe
                            if(resultado.next())
                            {
                                System.out.println("------------------------------");
                                System.out.println("Informacion de la tabla " + nombreTabla);
                                System.out.println("Catalogo: " + resultado.getString("TABLE_CAT") + " | Esquema: " + resultado.getString("TABLE_SCHEM") + " | Nombre de la tabla: " + resultado.getString("TABLE_NAME") + " | Tipo: " + resultado.getString("TABLE_TYPE")); 
                            
                            }
                            else
                            {
                                System.out.println("-ERROR- La tabla no existe en la base de datos.");
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("-ERROR- El nombre de la tabla no puede ser nulo.");
                            break;
                            
                        }
                      
                        System.out.println("------------------------------");
                        break;


                    case 4:
                        //Mostrar columnas de una tabla
                        System.out.println("-MOSTRAR COLUMNAS DE UNA TABLA-");
                        //Pedimos nombre de tabla
                        System.out.println("Inserte nombre de la tabla: ");
                        sc.nextLine();
                        nombreTabla = sc.nextLine();
                        
                        //Comprobamos que no sea nula
                        if(!nombreTabla.equals(""))
                        {
                            resultado = datos.getColumns(db, null, nombreTabla, null);
                            //Comprobamos que existe
                            if(resultado.next())
                            {   System.out.println("Columnas de la tabla " + nombreTabla);
                                System.out.println("------------------------------");
                                while(resultado.next())
                                {
                                    System.out.println("Nombre de tabla: " + resultado.getString("TABLE_NAME") + " | Nombre columna: " + resultado.getString("COLUMN_NAME") + " | Tipo: " + resultado.getString("TYPE_NAME") + " | Tamaño: " + resultado.getInt("COLUMN_SIZE") + " | ¿Puede ser nula?: " + resultado.getString("IS_NULLABLE")); 
                                }
                            }
                            else
                            {
                                System.out.println("-ERROR- La tabla no existe en la base de datos.");
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("-ERROR- El nombre de la tabla no puede ser nulo.");
                            break;
                            
                        }

                        System.out.println("------------------------------");
                        break;
                    case 5:
                        System.out.println("-MOSTRAR CLAVES PRIMARIAS DE UNA TABLA-");
                        //Pedimos nombre de tabla
                        System.out.println("Inserte nombre de la tabla: ");
                        sc.nextLine();
                        nombreTabla = sc.nextLine();
                        
                        //Comprobamos que no sea nula
                        if(!nombreTabla.equals(""))
                        {
                            resultado = datos.getPrimaryKeys(db, null, nombreTabla);
                            
                            //Comprobamos que existe
                           //if(resultado.next())
                            //{
                               System.out.println("Primary Keys de la tabla " + nombreTabla);
                               System.out.println("------------------------------");
                                //System.out.println("Nombre de tabla: " + resultado.getString("TABLE_NAME") + " | Nombre columna: " + resultado.getString("COLUMN_NAME") + " | Primary key: " + resultado.getString("PK_NAME")); 
                                //result = datos.getColumns(db, null, nombreTabla, null);
                               while(resultado.next())
                                {
                                    //if(!resultado.next())
                                      //  break;
                                    System.out.println("Nombre de tabla: " + resultado.getString("TABLE_NAME") + " | Nombre columna: " + resultado.getString("COLUMN_NAME") + " | Primary key: " + resultado.getString("PK_NAME")); 
                                    //resultado.next();
                                    
                                }
                               
                            //}
                            //else
                            //{
                            //    System.out.println("-ERROR- La tabla no existe en la base de datos.");
                            //    break;
                            //}
                        }
                        else
                        {
                            System.out.println("-ERROR- El nombre de la tabla no puede ser nulo.");
                            break;
                            
                        }

                        System.out.println("------------------------------");
                        break;
                    case 6:
                        System.out.println("-MOSTRAR CLAVES EXTERNAS ASOCIADAS A LA CLAVE PRIMARIA DE UNA TABLA-");
                        System.out.println("Inserte nombre de la tabla: ");
                        sc.nextLine();
                        nombreTabla = sc.nextLine();
                        
                        //Comprobamos que no sea nula
                        if(!nombreTabla.equals(""))
                        {
                            resultado = datos.getImportedKeys(db, null, nombreTabla);
                            
                            
                               System.out.println("Foreign Keys de la tabla " + nombreTabla);
                               System.out.println("------------------------------");
                                while(resultado.next())
                                {
                                   System.out.println("Nombre de tabla de la PK: " + resultado.getString("PKTABLE_NAME") + " | Columna Primary Key: " + resultado.getString("PKCOLUMN_NAME") + " | Columna Foreign key: " + resultado.getString("FKCOLUMN_NAME")); 
                                }
                        }
                        else
                        {
                            System.out.println("-ERROR- El nombre de la tabla no puede ser nulo.");
                            break;
                            
                        }

                        System.out.println("------------------------------");
                        break;
                    case 7:
                        System.out.println("-MOSTRAR PROCEDIMIENTOS ALMACENADOS-");
                        resultado = datos.getProcedures(db, null, null);
                        System.out.println("Procedimientos almacenados de la base de datos " + db);
                        System.out.println("------------------------------");
                        while(resultado.next())
                        {
                           System.out.println("Nombre del procedimiento: " + resultado.getString("PROCEDURE_NAME") + " | Comentarios: " + resultado.getString("REMARKS")); 
                        }
                        

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

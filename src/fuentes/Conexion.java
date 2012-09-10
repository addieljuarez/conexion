/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.util.Password;

/**
 *
 * @author addiel
 */
public class Conexion {

    static String url="jdbc:derby://localhost:1527/bd1";
    static String usuario="bd1";
    static String password="bd1";
    
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
       Class.forName("org.apache.derby.jdbc.ClientDriver");
       SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
       
       Connection c =null;
       Statement s =null;
       ResultSet rs=null;
        try {
            c = DriverManager.getConnection(url, usuario, password);
            s = c.createStatement();
         //   s.execute("CREATE TABLE Alumno(boleta integer primary key, nombre varchar(25) not null, fecha_de_nacimiento date not null)");
         //   boolean n=s.execute("insert into Alumno values(1,'Juanito','04/09/2012')");
            System.out.println("Ya me conecte");
            
            rs = s.executeQuery("select * from Alumno");
            while (rs.next()){
                System.out.println("Boleta: "+rs.getString("boleta")+ ", "+"Nombre: "+rs.getString("nombre")+ ", Fecha De Nacimiento: "+
                        fmt.format(rs.getDate("fecha_de_nacimiento")));
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          if (s!=null){  
            try {
                s.close();
            } catch (SQLException ex) {
                
            }
          }
          if (c!=null){  
            try {
                c.close();
            } catch (SQLException ex) {
                
            }
          }
        }
    }
}

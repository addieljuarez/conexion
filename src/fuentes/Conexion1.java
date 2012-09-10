/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.util.Password;

/**
 *
 * @author addiel
 */
public class Conexion1 {

    static String url="jdbc:derby://localhost:1527/bd1";
    static String usuario="bd1";
    static String password="bd1";
    
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
       Class.forName("org.apache.derby.jdbc.ClientDriver");
       SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
       Scanner ent = new Scanner(System.in);
       Connection c =null;
       PreparedStatement ps1 =null;
       PreparedStatement ps2 =null;
       ResultSet rs=null;
        try {
            c = DriverManager.getConnection(url, usuario, password);
            ps1 = c.prepareStatement("Insert into Alumno(boleta, nombre, fecha_de_nacimiento) VALUES(?,?,?)");
            ps1.setInt(1, 4);
            ps1.setString(2, "Lucero");
            ps1.setDate(3, new Date(fmt.parse("06/09/2012").getTime()));
            ps1.executeUpdate();
            System.out.println("Ya lo inserte");
            
            ps2 = c.prepareStatement("select * from Alumno");
            rs = ps2.executeQuery();
            while (rs.next()){
                System.out.println("Boleta: "+rs.getString("boleta")+ ", "+"Nombre: "+rs.getString("nombre")+ ", Fecha De Nacimiento: "+
                        fmt.format(rs.getDate("fecha_de_nacimiento")));
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          if (ps1!=null){  
            try {
                ps1.close();
            } catch (SQLException ex) {
                
            }
          }
          if (ps2!=null){  
            try {
                ps2.close();
            } catch (SQLException ex) {
                
            }
          }
          if (rs!=null){  
            try {
                rs.close();
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


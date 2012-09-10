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
public class Conexion2 {

    static String url="jdbc:derby://localhost:1527/bd1";
    static String usuario="bd1";
    static String password="bd1";
    static Connection c =null;
    static PreparedStatement ps1 =null;
    static PreparedStatement ps2 =null;
    static   ResultSet rs=null;
    static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
       Class.forName("org.apache.derby.jdbc.ClientDriver");
       Scanner ent = new Scanner(System.in);
       int opcion=0;
       do{
           System.out.println("1.Agregar Alumno");
           System.out.println("2.Consultar Alumno");
           System.out.println("3.Salir");
           System.out.print("Elige una opcion: ");
           opcion= Integer.parseInt(ent.nextLine());
           switch (opcion) {
               case 1:
                    System.out.print("Dame la boleta: ");
                    int boleta = Integer.parseInt(ent.nextLine());
                    System.out.print("Dame el nombre: ");
                    String nombre = ent.nextLine();
                    System.out.print("Dame la fecha: ");
                    String fecha = ent.nextLine();
                    agregarAlumno(boleta, nombre, fecha);
                    break;
               case 2: consultarAlumno();
                    break;
           }
           
           
       }while(opcion!=3);
       
        
    }

    private static void agregarAlumno(int boleta, String nombre, String fecha) throws ParseException {
        try {
            c = DriverManager.getConnection(url, usuario, password);
            ps1 = c.prepareStatement("Insert into Alumno(boleta, nombre, fecha_de_nacimiento) VALUES(?,?,?)");
            ps1.setInt(1, boleta);
            ps1.setString(2, nombre);
            ps1.setDate(3, new Date(fmt.parse(fecha).getTime()));
            ps1.executeUpdate();
            System.out.println("Alumnno Agregado");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          if (ps1!=null){  
            try {
                ps1.close();
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

    private static void consultarAlumno() {
        try {
            c = DriverManager.getConnection(url, usuario, password);
            
            ps2 = c.prepareStatement("select * from Alumno");
            rs = ps2.executeQuery();
            while (rs.next()){
                System.out.println("Boleta: "+rs.getString("boleta")+ ", "+"Nombre: "+rs.getString("nombre")+ ", Fecha De Nacimiento: "+
                        fmt.format(rs.getDate("fecha_de_nacimiento")));
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          
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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author sebas
 */
public class BaseDeDatos {

    static public String DRIVER = "org.postgresql.Driver";
    static public String DBNAME = "df7nof0rr08vt4";
    static public String HOSTNAME = "ec2-52-203-160-194.compute-1.amazonaws.com";
    static public String PORT = "5432";
    static public String URL = "jdbc:postgresql://" + HOSTNAME + ":" + PORT + "/" + DBNAME;
    static public String USERNAME = "vhrezzjpiqdvwi";
    static public String PASSWORD = "f6605749682aa84f7c72919c4af459b609a1d0c9c6e788b2480f1f98fdde5265";

    
    /* static public String DBNAME   = "SEM";
   static public String HOSTNAME = "localhost";
   static  public String PORT="5432";
    static public String URL="jdbc:postgresql://"+HOSTNAME+":"+PORT+"/"+DBNAME;
   static public String USERNAME = "AdminDB";
    static public String PASSWORD = "1234";*/
   // public abstract boolean ConnectDB();
    public static Connection Conexion() {
        Connection cn = null;
        try {
            Class.forName(DRIVER);
            cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "No se pudo conectar al servidor ", ButtonType.OK);
            Mensaje.setTitle("Conexion con servidor");

            Mensaje.showAndWait();
        }
        return cn;

    }
}

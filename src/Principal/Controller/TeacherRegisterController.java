/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Principal.Database.BaseDeDatos;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class TeacherRegisterController implements Initializable {

    @FXML
    private Button btnRegistrar;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtAlias;
     Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public TeacherRegisterController() {
          con = BaseDeDatos.Conexion();
    }
    
public void ButtonAction(MouseEvent event) {

        if (event.getSource() == btnRegistrar) {
            if (Register().equals("Success")) {
                System.out.println("Dato agregado Correctamente");
            }else{
                System.out.println("Error");
        }
         
            
        }}
 private String Register() {
        String status = "Success";
        String nom = txtNombres.getText();
        String Ape = txtApellidos.getText();
           String Alias = txtAlias.getText();
           System.out.println("Entro register");
        if(nom.isEmpty() || Ape.isEmpty() || Alias.isEmpty()) {
            System.out.println( "No tiene datos");
            status = "Error";
        } else {
            //query
            System.out.println("");
            //Error de consistencia en la base de datos te pide dos apellidos pero en el prototipado te pide los dos apellidos juntos
            String[] apellidos=Ape.split(" ");
         int id=((int) (Math.random() * 1000 + 100));
            //Cometimos un error al crear la base de datos asi que asigne un valor aleatrio al id de docente al no ser auto incrmento 
            String sql = "INSERT INTO public.docentes(id_docente, nombre, apellido_paterno, apellido_materno, alias) VALUES (?, ?, ?, ?, ?)";
            try {
                preparedStatement = con.prepareStatement(sql);
                 preparedStatement.setInt(1, id);
                preparedStatement.setString(2, nom);
                preparedStatement.setString(3, apellidos[0]);
                preparedStatement.setString(4, apellidos[1]);
                 preparedStatement.setString(5, Alias);
                resultSet = preparedStatement.executeQuery();
              //  System.out.println(resultSet);
            
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
              
            }
        }
        
        return status;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

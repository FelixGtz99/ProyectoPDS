/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controller;

import Principal.Database.BaseDeDatos;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnIngresar;
    @FXML
    private TextField txtEmail;
     @FXML
    private TextField txtPass;
    
    
 Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
     public LoginController() {
        con = BaseDeDatos.Conexion();
    }

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtEmail.getText();
        String password = txtPass.getText();
        if(email.isEmpty() || password.isEmpty()) {
            System.out.println( "No tiene datos");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM usuarios Where correo = ? and contraseña = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("Enter Correct Email/Password");
                    status = "Error";
                } else {
                    System.out.println("Login Successful..Redirecting..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    @FXML
    public void ButtonAction(MouseEvent event) {

        if (event.getSource() == btnIngresar) {
            System.out.println("Entro en boton");
            if (logIn().equals("Success")) {
                 try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Principal/Views/Menu.fxml")));
                    stage.setScene(scene);
  
                    stage.show();
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
              
                 
            }   else{
             try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Principal/Views/AccountMenu.fxml")));
                    stage.setScene(scene);
  
                    stage.show();
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
              
                 
            } 
            }
        }
    }

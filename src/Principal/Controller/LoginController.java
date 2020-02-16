/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controller;

import java.io.IOException;
import java.net.URL;
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private Boolean logIn() {
        Boolean status = true;
        String email = txtEmail.getText();
        String password = txtPass.getText();
        if(email.isEmpty() || password.isEmpty()) {
            System.out.println("Elementos Vacios");
            status=false;
        } else {
            
            //String sql = "SELECT * FROM admins Where email = ? and password = ?";
            if (email.equals("123") && password.equals("123")) {
                System.out.println("Contraseña correcta");
            }else{
                System.out.println("Incorrecto");
            }
               
            }
        
        
        return status;
    }
    @FXML
    public void ButtonAction(MouseEvent event) {

        if (event.getSource() == btnIngresar) {
            System.out.println("Entro en boton");
            if (logIn()) {
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

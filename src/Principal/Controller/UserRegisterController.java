/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class UserRegisterController implements Initializable {

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnSalir;
   @FXML
   private TextField txtEmail;
   @FXML
   private TextField txtNombres;
   @FXML
   private TextField txtApellidos;
   @FXML
   private TextField txtExpediente;
   @FXML
   private TextField txtCarrera;
   @FXML
   private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
   private void register(ActionEvent event) {
       
   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EvaluateController implements Initializable {

    @FXML
    private Button btnEnviar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    @FXML
    private ComboBox cbDocente;
     @FXML
    private ComboBox cbMateria;
      @FXML
    private Slider slCalif;
public void ButtonAction(MouseEvent event) {

        if (event.getSource() == btnEnviar) {
      
            

            
        }
if (event.getSource() == btnAgregar) {
      
               

            
        }
if (event.getSource() == btnSalir) {
      
             

            
        }}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

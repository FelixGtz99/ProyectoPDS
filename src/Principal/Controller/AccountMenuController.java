
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AccountMenuController implements Initializable {

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnSalir;

    //Cambio de ventana
      public void ButtonAction(MouseEvent event) {

        if (event.getSource() == btnRegistrar) {
      
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Principal/Views/UserRegister.fxml")));
                    stage.setScene(scene);
  
                    stage.show();
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            
        }
        if (event.getSource() == btnIngresar) {
      
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Principal/Views/Login.fxml")));
                    stage.setScene(scene);
  
                    stage.show();
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            
        }
        if (event.getSource() == btnCerrar) {
      
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

            
        }
        if (event.getSource() == btnSalir) {
      
        

            
        }}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
    }    
    
}

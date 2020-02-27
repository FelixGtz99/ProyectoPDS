
package Principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class main extends Application {
   
    @Override
    public void start(Stage stage) throws Exception {
Parent root=FXMLLoader.load(getClass().getResource("Views/Menu.fxml"));
Scene scene= new Scene(root);
stage.setTitle("Sistema de evaluacion de maestros");
stage.setScene(scene);
stage.show();
 stage.setOnCloseRequest(evt -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro de que quiere cerrar la aplicacion?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.NO.equals(result)) {
            evt.consume();
        }
    });
        System.out.println(stage.isShowing());
    } public static void main(String[] args) {
        launch(args);    }

    
}

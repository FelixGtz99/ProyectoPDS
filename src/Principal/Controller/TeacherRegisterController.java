/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Controller;

import Validators.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void register(ActionEvent event) {
        List<Validator> validators = new ArrayList();
        //El NameValidator no está completo, le falta añadir algunas cosas.
        //In fact no estoy seguro que esto tenga algún propósito
        //Nono ya caí en cuenta, este validator es temporal pq me dio flojera hacer el real
        validators.add(new NameValidator());
        Validator comp = new CompositeValidator(validators);
        String names = txtNombres.getText();
        String surname = txtApellidos.getText();
        String alias = txtAlias.getText();
        Usuario info = new Usuario(names,surname, alias);
        List<String> errors = comp.validate(info);
        if(!errors.isEmpty()){
           String errordisplay = "";
            for (String error : errors) {
               errordisplay = errordisplay + error + "\n";
            }
            JOptionPane.showMessageDialog(null, errordisplay,
                       "Error de Registro", JOptionPane.WARNING_MESSAGE);
       } else{
           //Aquí es donde registraría los datos... si supiera como!
        }
    }
    
}

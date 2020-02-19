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
import java.util.ArrayList;
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
    private Button btnSalir;
    @FXML
    private ComboBox cbDocente;
     @FXML
    private ComboBox cbMateria;
      @FXML
    private Slider slCalif;
       Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
public void ButtonAction(MouseEvent event) {

        if (event.getSource() == btnEnviar) {
      
            

            
        }

if (event.getSource() == btnSalir) {
      
             

            
        }}
  public EvaluateController() {
        con = BaseDeDatos.Conexion();
    }
private void ListarMaestros(){
   String[] lista =new String[3];
   String docente=" ";
   cbDocente.getItems().clear();
    String sql = "SELECT  nombre, apellido_paterno, apellido_materno FROM docentes";
    try {
                preparedStatement = con.prepareStatement(sql);
                
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                lista[0]=resultSet.getString(1);
                    
                lista[1]=resultSet.getString(2);
               
                lista[2]=resultSet.getString(3);
                
                docente=lista[0]+" "+ lista[1]+" "+lista[2];
                   cbDocente.getItems().add(docente);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }

}
private void ListarMaterias(){
   String[] lista =new String[3];
   String docente=" ";
    String sql = "SELECT  nombre, apellido_paterno, apellido_materno FROM docentes";
    try {
                preparedStatement = con.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                lista[0]=resultSet.getString(1);
                lista[1]=resultSet.getString(2);
                lista[2]=resultSet.getString(3);
                docente=lista[0]+" "+ lista[1]+" "+lista[2];
                   cbDocente.getItems().add(docente);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListarMaestros();
        
    }    
    
}

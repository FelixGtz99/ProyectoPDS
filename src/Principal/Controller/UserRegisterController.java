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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.rmi.RemoteException;

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
   //No sé si esto es correcto pero así estaba en el fyret
   IDBAdapter posgre = BaseDeDatos.getDBAdapter();

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
       validators.add(new NameValidator());
       validators.add(new EmailRecordValidator());
       validators.add(new PasswordCareerValidator());
       Validator comp = new CompositeValidator(validators);
       String names = txtNombres.getText();
       String surnames = txtApellidos.getText();
       String email = txtEmail.getText();
       //El password hay dos maneras de hacerlo, en Swing se haría de la manera
       //comentada, pero parece que no funciona igual aquí
       //String password = new String(txtPassword.getText());
       String password = txtPassword.getText();
       String record= txtExpediente.getText();
       String career = txtCarrera.getText();
       Usuario info = new Usuario(names,surnames,email,career,password,record);
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
           boolean exists = false;
           try{
               Connection conexion = posgre.getConnection();
               String SQL = "INSERT INTO usuarios(expediente, nombre, apellido, contraseña, correo, carrera) VALUES(?,?,?,?,?,?)";
               String[] datos1 = new String[6];
               Statement st1 = conexion.createStatement();
               ResultSet rs1 = st1.executeQuery("SELECT * FROM usuarios");
               
               while(rs1.next()){
                   
                   datos1[0] = rs1.getString(1);
                   datos1[4] =rs1.getString(5);
                   
                   if(record.equals(datos1[0]) || datos1[4].equals(email)){
                       exists = true;
                   }
                   
                   
               }
               if(exists){
                   JOptionPane.showMessageDialog(null, "El expediente está asociado\na otro usuario.",
                       "Error de Registro", JOptionPane.WARNING_MESSAGE);
               } else{
                   PreparedStatement pst = conexion.prepareStatement(SQL);
                   pst.setInt(1,Integer.parseInt(record));
                   pst.setString(2,names);
                   pst.setString(3,surnames);
                   pst.setString(4,password);
                   pst.setString(5,email);
                   pst.setString(6,career);
                   
                   pst.executeUpdate();
                   
               }
               
           } catch(SQLException ex){
               System.out.println(ex);
               //esta madre no sé pq da error
           } catch(RemoteException ex){
               System.out.println(ex);
           }
           
       }
   }
    
}

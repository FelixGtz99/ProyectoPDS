
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller implements Initializable {
    //Elementos Generales   
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    //Elementos AccountMenu
    @FXML private Button btnRegistrarAM;
    @FXML private Button btnIngresarAM;
    @FXML private Button btnCerrarAM;
    @FXML private Button btnSalirAM;
    //Elementos Evaluate
    @FXML private Button btnEnviarEV;
    @FXML private Button btnSalirEV;
    @FXML private ComboBox cbDocenteEV;
    @FXML private ComboBox cbMateriaEV;
    @FXML private Slider slCalifEV;
    //Elementos Evaluation
    @FXML private Button btnSalirEN;
    @FXML private Button btnVotarEN;
    @FXML private Button btnLikeEN;
    @FXML private Button btnDislikeEN;
    //Elementos Login 
    @FXML private Button btnIngresarL;
    @FXML private TextField txtEmailL;
    @FXML private TextField txtPassL;
    //Elementos Menu
    @FXML private Button btnCuentaMenu;
    @FXML private Button btnRDMenu;
    @FXML private Button btnREMenu;
    @FXML private Button btnBuscarMenu;
    @FXML private Button btnARMenu;
    //Elementos Recent Activity
    @FXML private Button btnLikeRA;
    @FXML private Button btnDislikeRA;
    @FXML private Button btnSalirRA;
    //Elementos Searcher
    @FXML private Button btnSalirS;
    @FXML private Button btnBuscarS;
    @FXML private Button btnEvaluarS;
    @FXML private Button btnEvaluacionesS;
    @FXML private RadioButton rbDocenteS;
    @FXML private RadioButton rbMateriaS;
    // Elementos Teacher Register
    @FXML private Button btnRegistrarTR;
    @FXML private TextField txtNombresTR;
    @FXML private TextField txtApellidosTR;
    @FXML private TextField txtAliasTR;
    //
    
  public void ButtonAction(MouseEvent event) {
      //Botones AccountMenu
      if (event.getSource()==btnRegistrarAM) {
          ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnIngresarAM) {
          ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnCerrarAM) {
          ChangeView("Menu",event);
      }
      if (event.getSource()==btnSalirAM) {
         // ChangeView("UserRegister",event);
      }
      //Botones Evaluate
      if (event.getSource()==btnSalirEV) {
         // ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnEnviarEV) {
         // ChangeView("UserRegister",event);
      }
      //Botones Evaluation
      if (event.getSource()==btnSalirEN) {
         // ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnVotarEN) {
         // ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnLikeEN) {
         // ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnDislikeEN) {
         // ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnIngresarL) {
          if (logIn().equals("Success")) {
             ChangeView("Menu",event); 
          }else{
             ChangeView("AccountMenu",event);
          }
      }
      //Botones Menu
      if (event.getSource()==btnCuentaMenu) {
          ChangeView("AccountMenu",event);
      }
      if (event.getSource()==btnRDMenu) {
          ChangeView("TeacherRegister",event);
      }
      if (event.getSource()==btnREMenu) {
          ChangeView("Evaluate",event);
      }
      if (event.getSource()==btnBuscarMenu) {
          ChangeView("Searcher",event);
      }
      if (event.getSource()==btnARMenu) {
          ChangeView("RecentActivity",event);
      }
      //Botones Recent Activity
      if (event.getSource()==btnLikeRA) {
          
      }
      if (event.getSource()==btnDislikeRA) {
          
      }
      if (event.getSource()==btnSalirRA) {
          
      }
      // Botones Searcher
      if (event.getSource()==btnBuscarS) {
          
      }
      if (event.getSource()==btnSalirS) {
          
      }
      if (event.getSource()==btnEvaluarS) {
          ChangeView("Evaluate", event);
      }
      if (event.getSource()==btnEvaluacionesS) {
          ChangeView("Evaluation",event);
      }
      //Botones Teacher Register
      if (event.getSource()==btnRegistrarTR) {
            if (Register().equals("Success")) {
                System.out.println("Dato agregado Correctamente");
            }else{
                System.out.println("Error");
        }
      }
  
      
  }
   //Ingreso de sesion
    private String logIn() {
        String status = "Success";
        String email = txtEmailL.getText();
        String password = txtPassL.getText();
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
  public void ChangeView(String view, MouseEvent event){
  
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Principal/Views/"+view+".fxml")));
                    stage.setScene(scene);
  
                    stage.show();
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
  }
  private void ListarMaestros(){
   String[] lista =new String[3];
   String docente=" ";
   cbDocenteEV.getItems().clear();
    String sql = "SELECT  nombre, apellido_paterno, apellido_materno FROM docentes";
    try {
                preparedStatement = con.prepareStatement(sql);
                
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                lista[0]=resultSet.getString(1);
                    
                lista[1]=resultSet.getString(2);
               
                lista[2]=resultSet.getString(3);
                
                docente=lista[0]+" "+ lista[1]+" "+lista[2];
                   cbDocenteEV.getItems().add(docente);
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
                   cbDocenteEV.getItems().add(docente);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }

}
 private String Register() {
        String status = "Success";
        String nom = txtNombresTR.getText();
        String Ape = txtApellidosTR.getText();
           String Alias = txtAliasTR.getText();
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
 public Controller() {
        con = BaseDeDatos.Conexion();
    }
 @Override
 
    public void initialize(URL url, ResourceBundle rb) {
     //MetodosEvaluate
     ListarMaestros();
    }    
    
}

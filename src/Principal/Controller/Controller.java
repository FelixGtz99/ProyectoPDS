
package Principal.Controller;

import Principal.Database.BaseDeDatos;
import Principal.Evaluacion;
import Principal.Maestro;
import Principal.Materia;
import Principal.User;
import Principal.Validators.TeacherValidator;
import Principal.Votos;
import Principal.todoDatos;
import Validators.CompositeValidator;
import Validators.EmailRecordValidator;
import Validators.NameValidator;
import Validators.PasswordCareerValidator;
import Validators.Usuario;
import Validators.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Controller implements Initializable {
    //Elementos Generales   
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    static Boolean  sta= false;
    static int UserID=0;
    static int sID=0, idM=0, idD=0;
    
    static String E,M;
    
    static int[] Likes;
    ArrayList<Votos> VotosL=new ArrayList<Votos>();
    ArrayList<Votos> VotosD=new ArrayList<Votos>();
     ArrayList<Maestro> Maestros=new ArrayList();
     ArrayList<User> Usuarios=new ArrayList();
     ArrayList<Materia> Materias=new ArrayList();
       ArrayList<Evaluacion> Evaluaciones=new ArrayList();
        ArrayList<todoDatos> EvaluacionesT=new ArrayList();
     @FXML private Pane Pane;
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
    @FXML private Slider sliCalifEV;
    @FXML private TextArea txtComentarioEV;
    //Elementos Evaluation
    @FXML private Button btnSalirEN;
    @FXML private Button btnLikeEN;
    @FXML private Button btnDislikeEN;
    @FXML private ListView listEvaluation;
    //Elementos Login 
    @FXML private Button btnIngresarL;
      @FXML private Button btnSalirL;
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
    @FXML private ListView listRA;
    //Elementos Searcher
    @FXML private Button btnSalirS;
    @FXML private Button btnBuscarS;
    @FXML private Button btnEvaluarS;
    @FXML private Button btnEvaluacionesS;
    @FXML private RadioButton rbDocenteS;
    @FXML private RadioButton rbMateriaS;
    @FXML private TextField txtBuscarS;
    @FXML private ListView listMateriaS;
    @FXML private ListView listDocenteS;
    // Elementos Teacher Register
    @FXML private Button btnRegistrarTR;
    @FXML private Button btnSalirTR;
    @FXML private TextField txtNombresTR;
    @FXML private TextField txtApellidosTR;
    @FXML private TextField txtAliasTR;
    //Elementos User Register
    @FXML private Button btnRegistrarUR;
     @FXML private Button btnSalirUR;
     @FXML private TextField txtNombresUR;
     @FXML private TextField txtApellidosUR;
     @FXML private TextField txtEmailUR;
     @FXML private TextField txtPasswordUR;
     @FXML private TextField txtExpedienteUR;
     @FXML private ComboBox cbCarreraUR;
  @FXML private Hyperlink link;
    @FXML private CheckBox Check;
 
   
     public void ButtonAction(MouseEvent event) throws SQLException {
      //Botones AccountMenu
      if (event.getSource()==btnRegistrarAM) {
          ChangeView("UserRegister",event);
      }
      if (event.getSource()==btnIngresarAM) {
          ChangeView("Login",event);
      }
      if (event.getSource()==btnCerrarAM) {
          sta=false;
          ChangeView("Menu",event);
          
      }
      if (event.getSource()==btnSalirAM) {
       ChangeView("Menu",event); 
      }
      //Botones Evaluate
      if (event.getSource()==btnSalirEV) {
      ChangeView("Menu",event); 
      }
      if (event.getSource()==btnEnviarEV) {
          String Result=Guardar();
          if (Result.equals("Exito")) {
               ChangeView("Menu",event);
          }else{
              System.out.println(Result);
          }
      }
      //Botones Evaluation
      if (event.getSource()==btnSalirEN) {
          ChangeView("Menu",event);
      }
      
      if (event.getSource()==btnLikeEN) {
          try {
              // ChangeView("UserRegister",event);
              EvaluationLikes();
             
          } catch (SQLException ex) {
              Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      if (event.getSource()==btnDislikeEN) {
          try {
              // ChangeView("UserRegister",event);
              EvaluationDislikes();
             btnLikeEN.setVisible(false);
              btnDislikeEN.setVisible(false);
          } catch (SQLException ex) {
              Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      //Botones Login
      if (event.getSource()==btnIngresarL) {
          UserID=logIn();
          if (UserID!=0) {
              System.out.println(UserID);
              sta=true;
                Alert Usuario = new Alert(Alert.AlertType.INFORMATION, "Ingresaste con exito", ButtonType.OK);
                Usuario.setTitle("Ingreso");
                
                Usuario.showAndWait();
              ChangeView("Menu",event); 
            
            
          }else{
             ChangeView("AccountMenu",event);
          }
      }
            if (event.getSource()==btnSalirL) {
         
             ChangeView("Menu",event);
          
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
          try {
              RALikes();
              
              
          } catch (SQLException ex) {
              Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      if (event.getSource()==btnDislikeRA) {
          try {
              RADislikes();
             
          } catch (SQLException ex) {
              Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      if (event.getSource()==btnSalirRA) {
          ChangeView("Menu",event); 
      }
      // Botones Searcher
      if (event.getSource()==btnBuscarS) {
          if (rbMateriaS.isSelected()) {
              BuscarMateria();
          }if (rbDocenteS.isSelected()) {
              BuscarDocente();
          }
          
      }
      if (event.getSource()==btnSalirS) {
          ChangeView("Menu",event);
          
      }
      if (event.getSource()==btnEvaluarS) {
          ChangeView("Evaluate", event);
      }
      if (event.getSource()==btnEvaluacionesS) {
          if (!listDocenteS.getItems().isEmpty() || !listMateriaS.getItems().isEmpty()) {
          E=(String)listDocenteS.getSelectionModel().getSelectedItem();
           M=(String)listMateriaS.getSelectionModel().getSelectedItem();
        ChangeView("Evaluation",event);
     }
      }
      //Botones Teacher Register
      if (event.getSource()==btnRegistrarTR) {
            if (Register().equals("Success")) {
                System.out.println("Dato agregado Correctamente");
                   ChangeView("Menu",event);
                  Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Maestro Registrado Correctamente", ButtonType.OK);
                Mensaje.setTitle("Maestro");
                
                Mensaje.showAndWait();
            }else{
                System.out.println("Error");
        }
      }
       if (event.getSource()==btnSalirTR) {
           
                   ChangeView("Menu",event);
                
        
      }
  //Botones User Register
      if (event.getSource()==btnRegistrarUR) {
          UserRegister();
          if (UserID!=0) {
              ChangeView("Menu", event);
          }
      }
       if (event.getSource()==btnSalirUR) {
         
              ChangeView("Menu", event);
          
      }
       if (event.getSource()==link) {
          Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Terminos y condiciones");
alert.setWidth(800);

alert.setContentText("1. Aceptación de los términos" +
"Al momento de empezar a utilizar cualquiera de los servicios proporcionados por nuestro Sistema de Evaluación de Docentes (\"SEM\"), usted acepta y está sujeto a los siguientes términos y condiciones presentados a continuación (\"Términos del Sistema\")." +
"Cualquier cambio o funcionalidad que sea agregada al servicio actual en el futuro, también estará sujeta a sus correspondientes términos de servicio. SEM se reservará el derecho de actualizar y cambiar estos términos y condiciones en cualquier momento que se considere pertinente, usted se verá notificado de estos cambios al momento de registrarse en la aplicación. Sin embargo, si usted continúa utilizando el sistema después de una actualización o modificación de los términos del servicio, significa que usted está de acuerdo con los mismos. Le recomendamos revisar periódicamente los términos de servicio para saber si algún cambio en los mismos tendrá un impacto sobre su experiencia en la aplicación." +
"2. Condiciones para la Creación de una Cuenta " +
"     2.1. Usted deberá ser mayor de 18 años\n" +
"      2.2. Usted debe proporcionar su nombre completo de acuerdo con como aparece en su identificación oficial, fecha de nacimiento, una dirección de correo electrónico institucional válida y cualquier otro tipo de información necesaria para completar los campos del formulario solicitado durante el proceso de creación de una cuenta." +
"     2.3. Usted será responsable de mantener su usuario y contraseña de forma segura. El equipo de desarrollo de SEM no puede y no será responsable por daños o pérdidas ocasionados por no recordar o mantener la seguridad de su usuario y/o contraseña." +
"     2.4. Usted no podrá usar el servicio para fines ilícitos considerados ilegales, no autorizados o que violen cualquier instancia dentro del marco legal jurídico en la república mexicana." +
"     2.5. Usted es responsable de toda información o contenido que decida subir a la aplicación. No podrá transmitir o cargar en el sistema cualquier tipo de contenido de naturaleza negativa o destructiva que intente de cualquier forma agredir, insultar, discriminar, y atentar hacia la integridad física o moral de algún docente, de ser así nos veremos obligados a eliminar este contenido malicioso, o bien los usuarios lo harán de esta forma." +
"     " +
"     2.6. Cualquier tipo de violación u omisión a los términos y condiciones del sistema SEM serán castigados con sanciones que van desde 2 a 5 años de cárcel o bien una multa federal de $200,000 siguiendo el código penal federal, esto de acuerdo con la Ley Reglamentaria del artículo 27 constitucional según estipula el orden jurídico nacional mexicano." +
"" +
"3. Descripción Funcional del Sistema" +
"    3.1. Usted será capaz de registrar docentes en el sistema." +
"    3.2. Usted tendrá la opción de elegir si realizar una búsqueda de algún docente o materia." +
"    3.3. Usted será capaz de realizar evaluaciones a maestros, Se deberá ingresar un texto donde se vea expresada su opinión de forma respetuosa, y posteriormente asignar una calificación para el desempeño del docente que va en escala del 1 al 10." +
"    3.4. Usted tendrá la posibilidad de calificar las reviews/opiniones de los otros alumnos, pudiendo dar me gusta o no me gusta en estas mismas." +
"" +
"" +
"");

alert.showAndWait();
              
         
      }
  }
     
   //Ingreso de sesion
      public void getMaestros() throws SQLException{
  String SQL ="SELECT * FROM docentes";
  preparedStatement=con.prepareStatement(SQL);
  resultSet=preparedStatement.executeQuery();
  while(resultSet.next()){
      //int id_docente, String nombre, String apellido, String alias
  Maestro m= new Maestro(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
 
  Maestros.add(m);
  
  }
  }
       public void getUsuarios() throws SQLException{
  String SQL ="SELECT * FROM usuarios";
  preparedStatement=con.prepareStatement(SQL);
  resultSet=preparedStatement.executeQuery();
  while(resultSet.next()){
      //int expediente, String nombre, String apellido, String contraseña, String correo, String carrera
 User u= new User(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
   Usuarios.add(u);
  }
  
  }
   public void getMaterias() throws SQLException{
  String SQL ="SELECT * FROM materia";
  preparedStatement=con.prepareStatement(SQL);
  resultSet=preparedStatement.executeQuery();
  while(resultSet.next()){
//      
//int expediente, String nombre, String apellido, String contraseña, String correo, String carrera
 Materia m= new Materia(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3));
Materias.add(m);
  }
  
  } 
   public void getEvaluaciones() throws SQLException{
  String SQL ="SELECT * FROM evaluacion";
  preparedStatement=con.prepareStatement(SQL);
  resultSet=preparedStatement.executeQuery();
  while(resultSet.next()){
      //id_usuario, id_docentes, id_materia, calificacion, comentario, id_evaluacion, likes, dislikes
//int id_usuario, int id_docentes, int id_materia, int id_evaluacion, double calificacion, String likes, String dislkes   
 Evaluacion e= new Evaluacion(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(8),resultSet.getDouble(4),resultSet.getString(6),resultSet.getString(7),resultSet.getString(5));
Evaluaciones.add(e);
  }

  }   
   public void getEv(){
 
       int mae=0;
       int mat=0;
       for (int i = 0; i < Evaluaciones.size(); i++) {
  
           for (int j = 0; j < Maestros.size(); j++) {
               if (Evaluaciones.get(i).getId_materia()==Materias.get(j).getId_materia()) {
                   mat=j;
               }
    
           }
             for (int j = 0; j < Maestros.size(); j++) {
               if (Evaluaciones.get(i).getId_docentes()==Maestros.get(j).getId_docente()) {
                   mae=j;
               }
    
           }
           String maestro=Maestros.get(mae).toString();
           String materia=Materias.get(mat).toString();
           todoDatos t=new todoDatos(Evaluaciones.get(i).getId_evaluacion(),Evaluaciones.get(i).getCalificacion(),Evaluaciones.get(i).getComentario(),materia, maestro);
           EvaluacionesT.add(t);
         
       }
  }
   private int logIn() throws SQLException {
        //Este metodo comprueba que los campos de email y contraseña esten llenos 
        int id = 0;
        String email = txtEmailL.getText();
        String password = txtPassL.getText();
        if(email.isEmpty() || password.isEmpty()) {
            System.out.println( "No tiene datos");
            id = 0;
        } else {
   
            
            for (int i = 0; i < Usuarios.size(); i++) {
                System.out.println(Usuarios.size());
                if (email.equals(Usuarios.get(i).getCorreo()) && password.equals(Usuarios.get(i).getContraseña())) {
                    
                    id=Usuarios.get(i).getExpediente();
                    
                    System.out.println(id);
            } 
            }
           
        }
        
        return id;
    }
  public void EvaluationLikes() throws SQLException{
        //Se puede hacer mejor xd
        int id=0;
        Object e= listEvaluation.getSelectionModel().getSelectedItem();
        System.out.println(e.toString());
        for (int i = 0; i < Evaluaciones.size(); i++) {
            System.out.println(Evaluaciones.get(i).toString());
            if (e.equals(Evaluaciones.get(i).toString())) {
               id=Evaluaciones.get(i).getId_evaluacion();
                System.out.println(id);
            }
      }
        if (id!=0) {
          int likes;
    String SQL="SELECT likes FROM evaluacion WHERE id_evaluacion=?";
    preparedStatement=con.prepareStatement(SQL);
    
    preparedStatement.setInt(1,id);
    resultSet=preparedStatement.executeQuery();
    //listRA.getSelectionModel().selectNext();
    if(resultSet.next()){
        System.out.println("Entron en el if");
    likes= (resultSet.getInt(1))+1;
        System.out.println(likes);
        Votos v=new Votos(UserID, id);
        VotosL.add(v);
        for (int i = 0; i < VotosL.size(); i++) {
            if (!VotosL.get(i).equals(v)) {
                  String SQL2="UPDATE evaluacion SET likes=? WHERE id_evaluacion=? ";
    preparedStatement=con.prepareStatement(SQL2);
      preparedStatement.setInt(1, likes);
      preparedStatement.setInt(2, id);
     preparedStatement.executeUpdate();  
            }
            else{
                Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya voto ", ButtonType.OK);
                Mensaje.setTitle("Votos");
                
                Mensaje.showAndWait();
            }
        }
        

    }
      }
    }
   public void RALikes() throws SQLException{
        //Se puede hacer mejor xd
        //
        int id=0;
        Object e= listRA.getSelectionModel().getSelectedItem();
        System.out.println(e.toString());
        for (int i = 0; i < Evaluaciones.size(); i++) {
            System.out.println(Evaluaciones.get(i).toString());
            if (e.equals(Evaluaciones.get(i).toString())) {
               id=Evaluaciones.get(i).getId_evaluacion();
                System.out.println(id);
            }
      }
        if (id!=0) {
          int likes;
    String SQL="SELECT likes FROM evaluacion WHERE id_evaluacion=?";
    preparedStatement=con.prepareStatement(SQL);
    
    preparedStatement.setInt(1,id);
    resultSet=preparedStatement.executeQuery();
    //listRA.getSelectionModel().selectNext();
    if(resultSet.next()){
        System.out.println("Entron en el if");
    likes= (resultSet.getInt(1))+1;
        System.out.println(likes);
        Votos v=new Votos(UserID, id);
        VotosL.add(v);
        for (int i = 0; i < VotosL.size(); i++) {
            if (!VotosL.get(i).equals(v)) {
                  String SQL2="UPDATE evaluacion SET likes=? WHERE id_evaluacion=? ";
    preparedStatement=con.prepareStatement(SQL2);
      preparedStatement.setInt(1, likes);
      preparedStatement.setInt(2, id);
     preparedStatement.executeUpdate();  
            }
            else{
                Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya voto ", ButtonType.OK);
                Mensaje.setTitle("Votos");
                
                Mensaje.showAndWait();
            }
        }
        

    }
      }
    }
   public void RADislikes() throws SQLException{
         int id=0;
        Object e= listRA.getSelectionModel().getSelectedItem();
        System.out.println(e.toString());
        for (int i = 0; i < Evaluaciones.size(); i++) {
            System.out.println(Evaluaciones.get(i).toString());
            if (e.equals(Evaluaciones.get(i).toString())) {
               id=Evaluaciones.get(i).getId_evaluacion();
                System.out.println(id);
            }
      }
        if (id!=0) {
          int likes;
    String SQL="SELECT dislikes FROM evaluacion WHERE id_evaluacion=?";
    preparedStatement=con.prepareStatement(SQL);
    
    preparedStatement.setInt(1,id);
    resultSet=preparedStatement.executeQuery();
    //listRA.getSelectionModel().selectNext();
    if(resultSet.next()){
        System.out.println("Entron en el if");
    likes= (resultSet.getInt(1))+1;
        System.out.println(likes);
        Votos v=new Votos(UserID, id);
        VotosD.add(v);
        for (int i = 0; i < VotosD.size(); i++) {
            if (!VotosD.get(i).equals(v)) {
                  String SQL2="UPDATE evaluacion SET dislikes=? WHERE id_evaluacion=? ";
    preparedStatement=con.prepareStatement(SQL2);
      preparedStatement.setInt(1, likes);
      preparedStatement.setInt(2, id);
     preparedStatement.executeUpdate();  
            }
            else{
                Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya voto ", ButtonType.OK);
                Mensaje.setTitle("Votos");
                
                Mensaje.showAndWait();
            }
        }
        

    }
      }
    }
   public void EvaluationDislikes() throws SQLException{
        //Se puede hacer mejor xd
        //
         int id=0;
        Object e= listEvaluation.getSelectionModel().getSelectedItem();
        System.out.println(e.toString());
        for (int i = 0; i < Evaluaciones.size(); i++) {
            System.out.println(Evaluaciones.get(i).toString());
            if (e.equals(Evaluaciones.get(i).toString())) {
               id=Evaluaciones.get(i).getId_evaluacion();
                System.out.println(id);
            }
      }
        if (id!=0) {
          int likes;
    String SQL="SELECT dislikes FROM evaluacion WHERE id_evaluacion=?";
    preparedStatement=con.prepareStatement(SQL);
    
    preparedStatement.setInt(1,id);
    resultSet=preparedStatement.executeQuery();
    //listRA.getSelectionModel().selectNext();
    if(resultSet.next()){
        System.out.println("Entron en el if");
    likes= (resultSet.getInt(1))+1;
        System.out.println(likes);
        Votos v=new Votos(UserID, id);
        VotosD.add(v);
        for (int i = 0; i < VotosD.size(); i++) {
            if (!VotosD.get(i).equals(v)) {
                  String SQL2="UPDATE evaluacion SET dislikes=? WHERE id_evaluacion=? ";
    preparedStatement=con.prepareStatement(SQL2);
      preparedStatement.setInt(1, likes);
      preparedStatement.setInt(2, id);
     preparedStatement.executeUpdate();  
            }
            else{
                Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya voto ", ButtonType.OK);
                Mensaje.setTitle("Votos");
                
                Mensaje.showAndWait();
            }
        }
        

    }
      }
    }
  public void UserRegister() throws SQLException{
      if (Check.isSelected()) {
          
      
    List<Validator> validators = new ArrayList();
       validators.add(new NameValidator());
       validators.add(new EmailRecordValidator());
       validators.add(new PasswordCareerValidator());
       Validator comp = new CompositeValidator(validators);
       String names = txtNombresUR.getText();
       String surnames = txtApellidosUR.getText();
       String email = txtEmailUR.getText();
       String password = txtPasswordUR.getText();
       String record= txtExpedienteUR.getText();
       String career = (String) cbCarreraUR.getSelectionModel().getSelectedItem();
       
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
           for (int i = 0; i < Usuarios.size(); i++) {
            if (record.equals(Usuarios.get(i).getExpediente())) {
               exists=true;
           }   
           }
           
                   
                   
               
               if(exists){
                   JOptionPane.showMessageDialog(null, "El expediente está asociado\na otro usuario.",
                       "Error de Registro", JOptionPane.WARNING_MESSAGE);
               } else{
                //int expediente, String nombre, String apellido, String contraseña, String correo, String carrera   
                  
                  
                  String sql="INSERT INTO usuarios(expediente, nombre, apellido, contraseña, correo, carrera) VALUES(?,?,?,?,?,?)";
     try{
               UserID=Integer.parseInt(record);
                      preparedStatement=con.prepareStatement(sql);
          preparedStatement.setInt(1,UserID);
                  preparedStatement.setString(2,names);
                  preparedStatement.setString(3,surnames);
                  preparedStatement.setString(4,password);
                  preparedStatement.setString(5,email);
                  preparedStatement.setString(6,career);
                   
                  preparedStatement.executeUpdate();
                  
                                     Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Usuario Registrado Correctamente", ButtonType.OK);
                Mensaje.setTitle("Usuario");
                
                Mensaje.showAndWait();
                   
                          }catch(Exception e){
                          
                                     Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Contraseña no validaa", ButtonType.OK);
                Mensaje.setTitle("");
                
                Mensaje.showAndWait();
                   
                          }
     
    
               }
               
  }
           
       
}else{
            Alert Mensaje = new Alert(Alert.AlertType.INFORMATION, "Es necesario que acepte terminos y condiciones ", ButtonType.OK);
                Mensaje.setTitle("Alerta");
                
                Mensaje.showAndWait();
      }
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
      for (int i = 0; i < Maestros.size(); i++) {
          cbDocenteEV.getItems().add(Maestros.get(i).toString());
         
      }

}
  public String ConsultarNombreDocente(int n){
  String nombre=" ";
   String sql = "SELECT nombre, apellido FROM docentes WHERE id_docente=?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nombre=resultSet.getString(1)+ " "+resultSet.getString(2);
                System.out.println(nombre);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
                
  return nombre;
  }
  public String ConsultarNombreMateria(int n){
  String nombre=" ";
   String sql = "SELECT nombre_materia FROM materia WHERE id_materia=?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                   nombre=resultSet.getString(1); 
                                   System.out.println(nombre);

            }
     
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
                
  return nombre;
  }
   
   
   //Retorna ID Evaluacion
   public int ConsultarIDEvaluacion(String Comentario, Double Calif){
   int id=0;
  
   String SQL="SELECT id_evaluacion FROM evaluacion WHERE comentario=? and calificacion=?";
        try {
            preparedStatement=con.prepareStatement(SQL);
            preparedStatement.setString(1, Comentario);
            preparedStatement.setDouble(2, Calif);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
             
                   id=resultSet.getInt(1); 
                
            }else{
                System.out.println("Fallo");
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

   return id;
   }
   
   //Muestra lista en Recent Activity
  public void ListarRA(){

          for (int i = 0; i < EvaluacionesT.size(); i++) {
              listRA.getItems().add(EvaluacionesT.get(i).toString());
              if (i==4) {
                  break;
              }
 
                  }
             
  }
  private int ConsultarIDMateria(){
 int id=0;
  String Materia=(String) cbMateriaEV.getSelectionModel().getSelectedItem().toString();
       System.out.println(Materia);
  
  if(Materia.isEmpty()) {
            System.out.println( "No tiene datos");
            id = 0;
        } else {
            
            for (int i = 0; i < Materias.size(); i++) {
                if (Materias.get(i).toString().equals(Materia)) {
                    id=Materias.get(i).getId_materia();
                }
      }
            
        }
        System.out.println(id);
  return id;
  }
  private int ConsultarLastIDD(){
            int id=0;
            System.out.println( "No tiene datos");
            id = 0;
            String sql = "SELECT Count(*) FROM docentes" ;
            try {
                preparedStatement = con.prepareStatement(sql);
    
                          
                resultSet = preparedStatement.executeQuery();
                
                if (!resultSet.next()) {
                    System.out.println("Error");
                   
                    
                } else {
                    id=resultSet.getInt(1);
                    System.out.println("Insercion completa");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                id = 0;
            
        }
     
  return id;
  }
  private int ConsultarLastIDEvaluation(){
            int id=0;
            System.out.println( "No tiene datos");
            id = 0;
            String sql = "SELECT Count(*) FROM evaluacion" ;
            try {
                preparedStatement = con.prepareStatement(sql);
    
                          
                resultSet = preparedStatement.executeQuery();
                
                if (!resultSet.next()) {
                    System.out.println("Error");
                   
                    
                } else {
                    id=resultSet.getInt(1);
                    System.out.println("Insercion completa");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                id = 0;
            
        }
     
  return id;
  }
  private int ConsultarIDDocente(){
  int id=0;
  String Docente=(String) cbDocenteEV.getSelectionModel().getSelectedItem().toString();
       System.out.println(Docente);
  
  if(Docente.isEmpty()) {
            System.out.println( "No tiene datos");
            id = 0;
        } else {
            
            for (int i = 0; i < Maestros.size(); i++) {
                if (Maestros.get(i).toString().equals(Docente)) {
                    id=Maestros.get(i).getId_docente();
                }
      }
            
        }
        System.out.println(id);
  return id;
  }
  private void BuscarDocente(){
  
  String Docente=txtBuscarS.getText();
       System.out.println(Docente);
       
String[] Datos=new String[2];
  listDocenteS.getItems().clear();
  if(Docente.isEmpty()) {
            System.out.println( "No tiene datos");
          
            
        } else {
        for (int i = 0; i < Maestros.size(); i++) {
          
            //System.out.println(Maestros.get(i).toString().contains(Docente));
            if (Maestros.get(i).toString().contains(Docente)) {
          listDocenteS.getItems().add(Maestros.get(i).toString());
      }}
        }
        
  
  }
  private void BuscarMateria(){
  
  String materia=txtBuscarS.getText();
       
  listMateriaS.getItems().clear();

  if(materia.isEmpty()) {
            System.out.println( "No tiene datos");
          
        } else {
        
           
           for (int i = 0; i < Materias.size(); i++) {
          
            //System.out.println(Maestros.get(i).toString().contains(Docente));
            if (Materias.get(i).toString().contains(materia)) {
          listMateriaS.getItems().add(Materias.get(i).toString());
      }}    
        }
        
  
  }
  private String Guardar(){
  String suc="Error";
  int idMateria=ConsultarIDMateria();
  int idDocente=ConsultarIDDocente();
  int idEvaluacion=ConsultarLastIDEvaluation()+1;
  String comentario=txtComentarioEV.getText();
  int cali=(int)sliCalifEV.getValue();
  
      if (idMateria!=0||idDocente!=0) {
           String sql = "SELECT * FROM evaluacion Where id_materia= ? and id_docentes=? and id_usuario=? " ;
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, idMateria);
                preparedStatement.setInt(2, idDocente);   
                preparedStatement.setInt(3, UserID);    
                resultSet = preparedStatement.executeQuery();
                
                if (!resultSet.next()) {
                     String sql2 = "INSERT INTO public.evaluacion(id_usuario, id_docentes, id_materia, calificacion, comentario, likes, dislikes, id_evaluacion) VALUES (?, ?, ?, ?, ?, 0, 0,?)";
            try {
               
                preparedStatement = con.prepareStatement(sql2);
                 preparedStatement.setInt(1, UserID);
                preparedStatement.setInt(2, idDocente);
                preparedStatement.setInt(3, idMateria);
                preparedStatement.setInt(4, cali);
                 preparedStatement.setString(5, comentario);
                  preparedStatement.setInt(6, idEvaluacion);
                resultSet = preparedStatement.executeQuery();
              //  System.out.println(resultSet);
            
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
              
            }
                    suc="Exito";
                   
                    
                } else {
                    System.out.println("Ya Existe");
                    suc="Ya Existe";
                    
                   
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
      }else{
      suc="Por favor inserte todos los datos";
      }
  
  return suc;
  }
  private void ListarMaterias(){
    for (int i = 0; i < Materias.size(); i++) {
          cbMateriaEV.getItems().add(Materias.get(i).toString());
         
      }


}
   private String Register() {
        String status=""; 
     List<Validator> validators = new ArrayList();
        //El NameValidator no está completo, le falta añadir algunas cosas.
        //In fact no estoy seguro que esto tenga algún propósito
        //Nono ya caí en cuenta, este validator es temporal pq me dio flojera hacer el real
        validators.add(new TeacherValidator());
        Validator comp = new CompositeValidator(validators);
        String nom= txtNombresTR.getText();
        String Ape = txtApellidosTR.getText();
        String Alias = txtAliasTR.getText();
        Usuario info = new Usuario(nom,Ape,Alias);
        List<String> errors = comp.validate(info);
        if(!errors.isEmpty()){
           String errordisplay = "";
            for (String error : errors) {
               errordisplay = errordisplay + error + "\n";
            }
            JOptionPane.showMessageDialog(null, errordisplay,
                       "Error de Registro", JOptionPane.WARNING_MESSAGE);
          
      
            status = "Error";
        } else {
            status = "Success";
            //query
            System.out.println("");
            //Error de consistencia en la base de datos te pide dos apellidos pero en el prototipado te pide los dos apellidos juntos
           
         int id=ConsultarLastIDD()+1;
            //Cometimos un error al crear la base de datos asi que asigne un valor aleatrio al id de docente al no ser auto incrmento 
            String sql = "INSERT INTO public.docentes(id_docente, nombre, apellido, alias) VALUES (?, ?, ?, ?)";
            try {
                preparedStatement = con.prepareStatement(sql);
                 preparedStatement.setInt(1, id);
                preparedStatement.setString(2, nom);
                preparedStatement.setString(3, Ape);
                 preparedStatement.setString(4, Alias);
                resultSet = preparedStatement.executeQuery();
              //  System.out.println(resultSet);
            
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
              
            }
        }
        
        return status;
    }
    public int getIdMateria(){
int n=0;
     for (int i = 0; i < Materias.size(); i++) {
         if ( cbMateriaEV.getSelectionModel().getSelectedItem().equals(Materias.get(i).getNombre_materia())) {
          n=Materias.get(i).getId_materia();
           //  System.out.println(n);
         }

     }
    
return n;
 }
     public int getIdDocente(){
int n=0;
     for (int i = 0; i < Maestros.size(); i++) {
         if ( cbDocenteEV.getSelectionModel().getSelectedItem().equals(Maestros.get(i).getNombre()+" "+Maestros.get(i).getApellido())) {
          n=Maestros.get(i).getId_docente();
         }
     }  
return n;
 }
     public void getItemsList(){
         //System.out.println(getIdMateria());
          listEvaluation.getItems().clear();
       int idMaestro=ConsultarIDDocente();
        int idMateria=ConsultarIDMateria();
         for (int i = 0; i < Evaluaciones.size(); i++) {
                 if (Evaluaciones.get(i).getId_docentes()==idMaestro && Evaluaciones.get(i).getId_materia()==idMateria) {
        listEvaluation.getItems().add(Evaluaciones.get(i).toString().replace("[", "").replace("]", ""));
                     
                 }
 
    
             
         }
                 
            
         
         
     }
 public void comboActionDocente() {
        cbDocenteEV.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
               // System.out.println("Selected value : " + newValue);

               //int id=ConsultarIDDocente();
                idD=ConsultarIDDocente();
            // getItemsList();
            
                
                             }
        });
        
    //count number of select actions
    System.out.println("Selection number: " );

    }
  public void comboActionMateria() {
        cbMateriaEV.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
             // listEvaluation.getItems().clear();
               // System.out.println("Selected value : " + newValue);
                 idM=ConsultarIDMateria();        
               getItemsList();
                     
               
                             }
        });
    //count number of select actions
    System.out.println("Selection number: " );

    }
   public int ConsultarIDEva(int n){
   int id=0;
   String materia;
   String SQL="SELECT nombre_materia FROM materia WHERE id_docente=?";
        try {
            preparedStatement=con.prepareStatement(SQL);
            preparedStatement.setInt(1, n);
            resultSet=preparedStatement.executeQuery();
            
            while (resultSet.next()){
            id=resultSet.getInt(1);
                  materia=resultSet.getString(1);
                    
                System.out.println(materia);
                   cbMateriaEV.getItems().add(materia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

   return id;
   }
 public Controller() {
        con = BaseDeDatos.Conexion();
    }
 @Override
 
    public void initialize(URL url, ResourceBundle rb) {
          //Pane.setPrefHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        Pane.autosize();
      
          try {
            getMaestros();
            getMaterias();
            getUsuarios();
            getEvaluaciones();
            getEv();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
     String dir=url.getFile();
        String[] d=dir.split("/");
      // System.out.println(sta);
       //System.out.println(UserID);
       //Si Menu Esta abierto
     if (d[d.length-1].equals("Menu.fxml")) {
         if (!sta) {
      
         
         btnREMenu.setVisible(false);
         btnRDMenu.setVisible(false);   
     }
     
     }
      //Si Account Menu Esta abierto
       if (d[d.length-1].equals("AccountMenu.fxml")) {
           if (!sta) {
           btnCerrarAM.setVisible(false);
           }else{
           btnIngresarAM.setVisible(false);
           btnRegistrarAM.setVisible(false);
           }
           
     }
       //Si searcher esta abierto
       if (d[d.length-1].equals("Searcher.fxml")) {
           if (!sta) {
               btnEvaluarS.setVisible(false);
           }
     }
       //Si evaluavion esta abierto
       if (d[d.length-1].equals("Evaluation.fxml")) {
           if (!sta) {
               btnLikeEN.setVisible(false);
               btnDislikeEN.setVisible(false);
              
           }
     }
       //Si RecentActivity esta abierto
         if (d[d.length-1].equals("RecentActivity.fxml")) {
             //
              ListarRA();
           if (!sta) {
               btnLikeRA.setVisible(false);
               btnDislikeRA.setVisible(false);
               
           }
          
     }
       //Si evaluate esta abierto
     if (d[d.length-1].equals("Evaluate.fxml")) {
         ListarMaestros();
             ListarMaterias();
        comboActionDocente();
         comboActionMateria();
     }
         if (d[d.length-1].equals("Evaluation.fxml")) {
         ListarMaestros();
          ListarMaterias();
           comboActionDocente();
         comboActionMateria();
         cbDocenteEV.setValue(E);
         cbMateriaEV.setValue(M);
        
     } 
                  if (d[d.length-1].equals("UserRegister.fxml")) {
         cbCarreraUR.setValue("ingeniería en sistemas de información");
        
     } 
    }    
    

    
}

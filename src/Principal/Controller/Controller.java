
package Principal.Controller;

import Principal.Database.BaseDeDatos;
import Principal.Validators.TeacherValidator;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Controller implements Initializable {
    //Elementos Generales   
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    static Boolean  sta= false;
    static int UserID=0;
    static int sID=0;
    static String E,M;
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
    @FXML private Button btnVerEN;
    @FXML private Button btnLikeEN;
    @FXML private Button btnDislikeEN;
    @FXML private ListView listEvaluation;
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
    @FXML private TextField txtNombresTR;
    @FXML private TextField txtApellidosTR;
    @FXML private TextField txtAliasTR;
    //Elementos User Register
    @FXML private Button btnRegistrarUR;
     @FXML private TextField txtNombresUR;
     @FXML private TextField txtApellidosUR;
     @FXML private TextField txtEmailUR;
     @FXML private TextField txtPasswordUR;
     @FXML private TextField txtExpedienteUR;
     @FXML private TextField txtCarreraUR;
 
  public void ButtonAction(MouseEvent event) {
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
      if (event.getSource()==btnVerEN) {
         int Materia=ConsultarIDMateria();
         int Docente=ConsultarIDDocente();
           String sql = "SELECT calificacion, comentario FROM evaluacion Where id_materia = ? and id_docentes = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, Materia);
                preparedStatement.setInt(2, Docente);
                resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()){
                  
                 String d="("+resultSet.getString(1)+"-/10)-"+resultSet.getString(2);
                listEvaluation.getItems().add(d);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
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
         // ChangeView("UserRegister",event);
         EvaluationDislike();
      }
      //Botones Login
      if (event.getSource()==btnIngresarL) {
          UserID=logIn();
          if (UserID!=0) {
              
              sta=true;
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
          RALike();
      }
      if (event.getSource()==btnDislikeRA) {
          RADislike();
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
                
            }else{
                System.out.println("Error");
        }
      }
  //Botones User Register
      if (event.getSource()==btnRegistrarUR) {
          UserRegister();
          if (UserID!=0) {
              ChangeView("Menu", event);
          }
      }
      
  }
   //Ingreso de sesion
    private int logIn() {
        int id = 0;
        String email = txtEmailL.getText();
        String password = txtPassL.getText();
        if(email.isEmpty() || password.isEmpty()) {
            System.out.println( "No tiene datos");
            id = 0;
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
                   
                    
                } else {
                    id=resultSet.getInt(1);
                    System.out.println("Login Successful..Redirecting..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                id = 0;
            }
        }
        
        return id;
    }
    public void EvaluationLikes() throws SQLException{
        //Se puede hacer mejor xd
        String E=(String) listEvaluation.getSelectionModel().getSelectedItem();
        String[] Datos=E.split("-");
        
        int[] Evaluacion=new int[4];
    String SQL="SELECT id_usuario, id_docentes, id_materia, likes FROM evaluacion WHERE calificacion=? and comentario=?";
    preparedStatement=con.prepareStatement(SQL);
    preparedStatement.setDouble(1,Double.parseDouble( Datos[0]));
    preparedStatement.setString(2, Datos[1]);
    resultSet=preparedStatement.executeQuery();
    
    if(resultSet.next()){
    Evaluacion[0]=resultSet.getInt(1);
    Evaluacion[1]=resultSet.getInt(2);
    Evaluacion[2]=resultSet.getInt(3);
    Evaluacion[3]=resultSet.getInt(4)+1;
    String SQL2="UPDATE evaluacion SET likes=? WHERE id_evaluation=? ";
    preparedStatement=con.prepareStatement(SQL2);
      preparedStatement.setInt(1, Evaluacion[4]);
      preparedStatement.setInt(2, Evaluacion[1]);
      preparedStatement.setInt(3, Evaluacion[2]);
      preparedStatement.setInt(4, Evaluacion[3]);
       
     resultSet=preparedStatement.executeQuery();
      
    }
    }
      public void EvaluationDislike(){
       
      
    
    }
       public void RADislike(){
       
      
    
    }
       public void RALike(){
       
      
    
    }
      
   
    public void UserRegister(){
    List<Validator> validators = new ArrayList();
       //El NameValidator no está completo, le falta añadir algunas cosas.
       validators.add(new NameValidator());
       validators.add(new EmailRecordValidator());
       validators.add(new PasswordCareerValidator());
       Validator comp = new CompositeValidator(validators);
       String names = txtNombresUR.getText();
       String surnames = txtApellidosUR.getText();
       String email = txtEmailUR.getText();
       //El password hay dos maneras de hacerlo, en Swing se haría de la manera
       //comentada, pero parece que no funciona igual aquí
       //String password = new String(txtPassword.getText());
       String password = txtPasswordUR.getText();
       String record= txtExpedienteUR.getText();
       String career = txtCarreraUR.getText();
       
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
               
               String SQL = "INSERT INTO usuarios(expediente, nombre, apellido, contraseña, correo, carrera) VALUES(?,?,?,?,?,?)";
               String SQL2="SELECT * FROM usuarios";
               String[] datos1 = new String[6];
             preparedStatement = con.prepareStatement(SQL2);
               resultSet = preparedStatement.executeQuery();
               
               while(resultSet.next()){
                   
                   datos1[0] = resultSet.getString(1);
                   datos1[4] =resultSet.getString(5);
                   
                   if(record.equals(datos1[0]) || datos1[4].equals(email)){
                       exists = true;
                   }
                   
                   
               }
               if(exists){
                   JOptionPane.showMessageDialog(null, "El expediente está asociado\na otro usuario.",
                       "Error de Registro", JOptionPane.WARNING_MESSAGE);
               } else{
                   preparedStatement  = con.prepareStatement(SQL);
                  preparedStatement.setInt(1,Integer.parseInt(record));
                  preparedStatement.setString(2,names);
                  preparedStatement.setString(3,surnames);
                  preparedStatement.setString(4,password);
                  preparedStatement.setString(5,email);
                  preparedStatement.setString(6,career);
                   
                  preparedStatement.executeUpdate();
                   UserID=Integer.parseInt(record);
                   
               }
               
           } catch(SQLException ex){
               System.out.println(ex);
               //esta madre no sé pq da error
           } catch(Exception ex){
               System.out.println(ex);
           }
           
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
   String[] lista =new String[2];
   String docente=" ";
   cbDocenteEV.getItems().clear();
    String sql = "SELECT  nombre, apellido FROM docentes";
    try {
                preparedStatement = con.prepareStatement(sql);
                
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                lista[0]=resultSet.getString(1);
                    
                lista[1]=resultSet.getString(2);
               
                
                
                docente=lista[0]+" "+ lista[1];
                   cbDocenteEV.getItems().add(docente);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }

}
  public String ConsultarNombreDocente(int n){
  String nombre=" ";
   String sql = "SELECT nombre, apellido FROM docentes WHERE id_docente=?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            resultSet = preparedStatement.executeQuery();
            
            nombre=resultSet.getString(1)+ resultSet.getString(2);
            
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
            nombre=resultSet.getString(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
                
  return nombre;
  }
  public void ListarRA(){
  String sql = "SELECT calificacion, id_docentes, id_materia, comentario FROM evaluacion ORDER BY id_evaluacion DESC";
            try {
                preparedStatement = con.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                  while(resultSet.next()){
                      String docente=ConsultarNombreDocente(resultSet.getInt(2));
                      String materia=ConsultarNombreMateria(resultSet.getInt(3));
                 String d="("+resultSet.getString(1)+"-/10)-"+docente+"-"+materia+"-"+resultSet.getString(4);
                listRA.getItems().add(d);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
  }
  private int ConsultarIDMateria(){
  int id=0;
  String Materia=(String) cbMateriaEV.getSelectionModel().getSelectedItem();
  if(Materia.isEmpty()) {
            System.out.println( "No tiene datos");
            id = 0;
        } else {
            //query
            String sql = "SELECT id_materia FROM materia Where nombre_materia = ?" ;
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, Materia);
               
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
  String Docente=(String) cbDocenteEV.getSelectionModel().getSelectedItem();
       System.out.println(Docente);
  String[] datos=Docente.split(" ");

  if(Docente.isEmpty()) {
            System.out.println( "No tiene datos");
            id = 0;
        } else {
            //query
            String sql = "SELECT id_docente FROM docentes Where nombre= ? and apellido=? " ;
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, datos[0]);
                preparedStatement.setString(2, datos[1]);
                          
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
        
            String sql = "SELECT nombre, apellido FROM docentes Where nombre=? OR apellido=? " ;
            try {
                
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, Docente);
                preparedStatement.setString(2, Docente);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                        Datos[0]=resultSet.getString(1);
                 Datos[1]=resultSet.getString(2);
                 String d=Datos[0]+" "+Datos[1];
                listDocenteS.getItems().add(d);
                }
                
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
        }
        
  
  }
     private void BuscarMateria(){
  
  String materia=txtBuscarS.getText();
       
  listMateriaS.getItems().clear();

  if(materia.isEmpty()) {
            System.out.println( "No tiene datos");
          
        } else {
        
            String sql = "SELECT nombre_materia FROM materia Where nombre_materia=? " ;
            try {
                
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, materia);
                
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                        
                
                
                listMateriaS.getItems().add(resultSet.getString(1));
                }
                
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
        }
        
  
  }
  private String Guardar(){
  String suc="Error";
  int idMateria=ConsultarIDMateria();
  int idDocente=ConsultarIDDocente();
  int idEvaluacion=ConsultarLastIDEvaluation();
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
   String materia;
  
    String sql = "SELECT  nombre_materia FROM materia";
    try {
                preparedStatement = con.prepareStatement(sql);
                
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                materia=resultSet.getString(1);
                    
             
                   cbMateriaEV.getItems().add(materia);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
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
 
 public Controller() {
        con = BaseDeDatos.Conexion();
    }
 @Override
 
    public void initialize(URL url, ResourceBundle rb) {
     //MetodosEvaluate
     
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
            
           if (!sta) {
               btnLikeRA.setVisible(false);
               btnDislikeRA.setVisible(false);
               
           }
            ListarRA();
     }
       //Si evaluate esta abierto
     if (d[d.length-1].equals("Evaluate.fxml")) {
         ListarMaestros();
         ListarMaterias();
        
     }
         if (d[d.length-1].equals("Evaluation.fxml")) {
         ListarMaestros();
         ListarMaterias();
         cbDocenteEV.setValue(E);
         cbMateriaEV.setValue(M);
        
     } 
    }    
    
}

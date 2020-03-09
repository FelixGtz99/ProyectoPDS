/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Validators;



import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class PasswordCareerValidator implements Validator<Usuario>{
   @Override
   public List<String> validate(Usuario info){
      List<String> errors = new ArrayList();
      
      String password = info.getPassword();
      String career = info.getCareer();
      
      boolean capsflag = false;
      boolean specialflag = false;
      boolean numflag = false;
      
      for (int i = 0; i < password.length(); i++) {
         char passchar = password.charAt(i);
         if(Character.isUpperCase(passchar)){
            capsflag = true;
         }
         
         if (Character.isDigit(passchar)) {
            for (int j = i+1; j < password.length(); j++) {
               char passchar2 = password.charAt(j);
               if(Character.isDigit(passchar2)){
                  numflag = true;
               }
            }
         }
         
         if(!Character.isAlphabetic(passchar) && !Character.isDigit(passchar)){
            for (int j = i+1; j < password.length(); j++) {
               char passchar2 = password.charAt(j);
               if(!Character.isAlphabetic(passchar2) && !Character.isDigit(passchar2)){
                  specialflag = true;
               }
            }
         }
         
         
      }
      
      if(career.isEmpty()){
         errors.add("El nombre de usuario no puede estar vacío");
      } else if(!career.toLowerCase().equals("ingeniería en sistemas de información") &&  !career.toLowerCase().equals("ingenieria en sistemas de informacion")){
          errors.add("Carrera no soportada");
      }
      if(password.isEmpty()){
         errors.add("La contraseña no puede estar vacía");
      }
      else if(password.length() < 8){
         errors.add("La contraseña debe ser mayor a 8 caracteres");
      }
      else if(password.length() > 16){
         errors.add("La contraseña debe ser menor a 16 caracteres");
      }
      else if(!capsflag || !numflag || !specialflag){
         errors.add("La contraseña debe contener al menos 1 mayúscula, 2 numeros y 2 caracteres especiales");
      }
      
      
      
      return errors;
      
   }
}

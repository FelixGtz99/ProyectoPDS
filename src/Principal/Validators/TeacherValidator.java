/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Validators;

import Validators.Usuario;
import Validators.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class TeacherValidator implements Validator<Usuario> {
   
   @Override
   public List<String> validate(Usuario info){
      List<String> errors = new ArrayList();
      String name = info.getName();
      String surname = info.getSurname();
      String alias = info.getAlias();
      
      if(name.isEmpty() && surname.isEmpty() && alias.isEmpty()){
         errors.add("Llene al menos uno de los campos");
      }
      
      
     
      
      return errors;
        
   }
}

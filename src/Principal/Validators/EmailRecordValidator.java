/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author usuario
 */
public class EmailRecordValidator implements Validator<Usuario>{

   @Override
   public List<String> validate(Usuario info) {
      List<String> errors = new ArrayList();
      String email = info.getEmail();
      String record = info.getRecord();
      boolean emailflag = false;
      
      if(record.charAt(0)=='2' || record.charAt(3)=='2' || record.length()==9){ 
         int recyear = 0;
         int currentyear = Calendar.getInstance().get(Calendar.YEAR);
         int currentmonth = Calendar.getInstance().get(Calendar.MONTH);
          try{
              recyear = Integer.parseInt(record.substring(1, 2));
              if(currentyear-2000<recyear || (currentyear-2000==recyear && currentmonth < 8)){
                  errors.add("Expediente inválido");
              }
          } catch(NumberFormatException e){
            errors.add("Expediente inválido");
          }
          
          
      } else if(record.isEmpty()){
          errors.add("Expediente no puede estar vacío");
      } else{
          errors.add("Expediente inválido");
      }
      
      if(email.isEmpty()){
         errors.add("E-mail no puede estar vacío");
      }
      if(errors.isEmpty()){
          if(!email.equals("a"+record+"@unison.mx") && !email.equals("a"+record+"@alumnos.unison.mx")){
              errors.add("El formato de E-mail es incorrecto");
          }
      }
      
      return errors;
   }
   
}

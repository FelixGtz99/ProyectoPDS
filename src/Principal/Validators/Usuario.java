/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;



/**
 *
 * @author Lighthouse
 */
public class Usuario {
   private String name;
   private String surname;
   private String email;
   private String career;
   private String password;
   private String record;

   public Usuario(String name, String surname, String email, String career, String password, String record) {
      this.name = name;
      this.surname = surname;
      this.email = email;
      this.career = career;
      this.password = password;
      this.record = record;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getCareer() {
      return career;
   }

   public void setCareer(String career) {
      this.career = career;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRecord() {
      return record;
   }

   public void setRecord(String record) {
      this.record = record;
   }

   
   
}

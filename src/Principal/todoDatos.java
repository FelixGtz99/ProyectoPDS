/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author PC
 */
public class todoDatos {
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

      public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

 

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
      public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_docentes() {
        return id_docentes;
    }

    public void setId_docentes(int id_docentes) {
        this.id_docentes = id_docentes;
    }

   

    public int getId_evaluacion() {
        return id_evaluacion;
    }

    public void setId_evaluacion(int id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislkes() {
        return dislkes;
    }

    public void setDislkes(String dislkes) {
        this.dislkes = dislkes;
    }

 
    int id_usuario, id_docentes, id_materia, id_evaluacion;
    double calificacion;
    String likes, dislkes, Comentario;
    String dato="("+Double.toString(calificacion)+"/10)"+" "+Comentario;

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    private String nombre_materia;

    public todoDatos(int id_evaluacion, double calificacion, String Comentario, String nombre_materia, String nombre) {
        this.id_evaluacion = id_evaluacion;
        this.calificacion = calificacion;
        this.Comentario = Comentario;
        this.nombre_materia = nombre_materia;
        this.nombre = nombre;
    }
 @Override
 public String toString(){
 return "("+calificacion+"/10) "+nombre+"  "+nombre_materia+" "+Comentario;
 }
    private String nombre, apellido, alias;
}

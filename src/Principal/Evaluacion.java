
package Principal;


public class Evaluacion {

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

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
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

    public Evaluacion(int id_usuario, int id_docentes, int id_materia, int id_evaluacion, double calificacion, String likes, String dislkes, String Comentario) {
        this.id_usuario = id_usuario;
        this.id_docentes = id_docentes;
        this.id_materia = id_materia;
        this.id_evaluacion = id_evaluacion;
        this.calificacion = calificacion;
        this.likes = likes;
        this.dislkes = dislkes;
             this.Comentario = Comentario;
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
    @Override
    public String toString(){
        if (Comentario==null) {
      return "("+Double.toString(calificacion)+"/10)"+" ";
        }
    return "("+Double.toString(calificacion)+"/10)"+" "+Comentario;
    }
}

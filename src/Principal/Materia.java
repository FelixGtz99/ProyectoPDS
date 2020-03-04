
package Principal;


public class Materia {

    public Materia(int id_materia, int id_docente, String nombre_materia) {
        this.id_materia = id_materia;
        this.id_docente = id_docente;
        this.nombre_materia = nombre_materia;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
    private int id_materia, id_docente;
    private String nombre_materia;
}

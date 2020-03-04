
package Principal;


public class Maestro {

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

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

    public Maestro(int id_docente, String nombre, String apellido, String alias) {
        this.id_docente = id_docente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
    }
    private int id_docente;
    private String nombre, apellido, alias;
    @Override
   public String toString(){
   return nombre+" "+apellido;
   }
}

package Hackathon2;

public class Contacto {
    String nombre;
    String apellido;
    String nombreApellido;
    String telefono;

    public Contacto() {
    }

    public Contacto(String nombreApellido, String telefono) {
        this.nombreApellido = nombreApellido;
        String[] partes = nombreApellido.split(" ", 2);
        this.nombre = partes[0];
        this.apellido = partes[1];
        this.telefono = telefono;
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

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

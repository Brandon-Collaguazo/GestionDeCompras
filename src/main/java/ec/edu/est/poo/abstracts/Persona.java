package ec.edu.est.poo.abstracts;

import java.util.Objects;

public abstract class Persona {
    private int id;
    private  String nombre;
    private String direccion;
    private String telefono;
    public Persona(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    public Persona() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id && Objects.equals(nombre, persona.nombre) && Objects.equals(direccion, persona.direccion) && Objects.equals(telefono, persona.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion, telefono);
    }

    @Override
    public String toString() {
        return super.toString() + "\nPersona{" +
                "Id: " + id +
                ", Nombre: '" + nombre + '\'' +
                ", Dirección: '" + direccion + '\'' +
                ", Teléfono: '" + telefono + '\'' +
                '}';
    }
}

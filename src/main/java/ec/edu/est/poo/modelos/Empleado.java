package ec.edu.est.poo.modelos;

import java.util.List;
import java.util.Objects;

public class Empleado extends Persona implements Buscable {
    private  String cargo;
    private Departamento departamento;

    public Empleado(int id, String nombre, String direccion, String telefono, String cargo, Departamento departamento) {
        super(id, nombre, direccion, telefono);
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public Empleado() {

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public SolicitudCompra realizarSolicitudCompra(int id, Departamento departamento, List<DetalleCompra> productos) {
        SolicitudCompra solicitudCompra = new SolicitudCompra(id, departamento, EstadoSolicitud.SOLICITADA, productos);
        System.out.println("Solicitud de compra creada: " + solicitudCompra);
        return solicitudCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(cargo, empleado.cargo) && Objects.equals(departamento, empleado.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo, departamento);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Cargo: " + getCargo() + "\n" +
                "Departamento: " + getDepartamento();
    }

    @Override
    public boolean coincideCon(String criterio) {
        return false;
    }
}

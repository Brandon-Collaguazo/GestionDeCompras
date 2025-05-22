package ec.edu.est.poo.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proveedor extends Persona implements Buscable {
    private List<Producto> productos;

    public Proveedor() {

    }

    public Proveedor(int id, String nombre, String direccion, String telefono, Producto producto) {
        super(id, nombre, direccion, telefono);
        this.productos = new ArrayList<>();
    }

    public List<Producto> getProducto() {
        return productos;
    }

    public void registrarProducto(int codigo, String nombre, String descripcion, double precio) {
        Producto newProducto = new Producto(codigo, nombre, descripcion, precio);
        productos.add(newProducto);
    }

    @Override
    public boolean coincideCon(String criterio) {
        return  String.valueOf(getId()).equals(criterio);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(productos, proveedor.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productos);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Productos: " + productos;
    }
}

package ec.edu.est.poo.modelos;

import java.util.Objects;

public class DetalleCompra {
    private Producto producto;
    private int cantidad;
    public DetalleCompra() {
    }

    public DetalleCompra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetalleCompra that = (DetalleCompra) o;
        return cantidad == that.cantidad && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, cantidad);
    }

    @Override
    public String toString() {
        return "DetalleCompra{" +
                "Producto: " + producto +
                ", Cantidad: " + cantidad +
                '}';
    }
}

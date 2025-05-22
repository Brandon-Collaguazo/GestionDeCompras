package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import ec.edu.est.poo.modelos.Producto;

public class VentanaProducto {
    private Frame frame;
    private TextField txtCodigo;
    private TextField txtNombre;
    private TextField txtDescripcion;
    private TextField txtPrecio;

    private Button btnAgregar, btnLimpiar, btnSalir;
    private List<Producto> listaProductos;

    public VentanaProducto(List<Producto> productos) {
        this.listaProductos = productos;
        construirInterfaz();
    }

    private void construirInterfaz() {
        frame = new Frame("Gestión de Productos");
        frame.setLayout(new FlowLayout());

        frame.add(new Label("Código:"));
        txtCodigo = new TextField(10);
        frame.add(txtCodigo);

        frame.add(new Label("Nombre:"));
        txtNombre = new TextField(20);
        frame.add(txtNombre);

        frame.add(new Label("Descripción:"));
        txtDescripcion = new TextField(30);
        frame.add(txtDescripcion);

        frame.add(new Label("Precio:"));
        txtPrecio = new TextField(10);
        frame.add(txtPrecio);

        btnAgregar = new Button("Agregar");
        btnLimpiar = new Button("Limpiar");
        btnSalir = new Button("Salir");

        frame.add(btnAgregar);
        frame.add(btnLimpiar);
        frame.add(btnSalir);

        // Evento agregar producto
        btnAgregar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nombre = txtNombre.getText().trim();
                String descripcion = txtDescripcion.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText());

                Producto nuevo = new Producto(codigo, nombre, descripcion, precio);
                listaProductos.add(nuevo);
                System.out.println("Producto agregado: " + nuevo);

                limpiarCampos();
            } catch (NumberFormatException ex) {
                System.out.println("Error: Código o precio inválido.");
            }
        });

        // Evento limpiar campos
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Evento salir
        btnSalir.addActionListener(e -> frame.dispose());

        // Cierre controlado
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setSize(500, 350);
        frame.setVisible(true);
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
    }
}

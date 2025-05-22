package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
//import java.util.Vector
import ec.edu.est.poo.modelos.*;

public class VentanaListaSolicitud extends Frame {
    private Choice chSolicitudes;
    private Choice chEstado;
    private TextArea areaDetalle;
    private Button btnAgregarProducto;
    private Button btnGuardar;
    private List<SolicitudCompra> solicitudes;
    private SolicitudCompra solicitudActual;

    public VentanaListaSolicitud(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        setTitle("Gestión de Solicitudes");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel superior: Selección de solicitud
        Panel pSuperior = new Panel(new GridLayout(3, 2, 10, 10));
        pSuperior.setBackground(new Color(230, 240, 255));
        Label lblSeleccion = new Label("Selecciona ID de Solicitud:");
        chSolicitudes = new Choice();

        for (SolicitudCompra s : solicitudes) {
            chSolicitudes.add(String.valueOf(s.getId()));
        }

        Label lblEstado = new Label("Estado de la Solicitud:");
        chEstado = new Choice();
        chEstado.add("PENDIENTE");
        chEstado.add("APROBADA");
        chEstado.add("RECHAZADA");

        pSuperior.add(lblSeleccion);
        pSuperior.add(chSolicitudes);
        pSuperior.add(lblEstado);
        pSuperior.add(chEstado);

        // Panel central: Detalles de solicitud
        areaDetalle = new TextArea(20, 60);
        areaDetalle.setEditable(false);

        // Panel inferior: botones
        Panel pInferior = new Panel();
        btnAgregarProducto = new Button("Agregar Producto");
        btnGuardar = new Button("Guardar Cambios");
        pInferior.add(btnAgregarProducto);
        pInferior.add(btnGuardar);

        add(pSuperior, BorderLayout.NORTH);
        add(areaDetalle, BorderLayout.CENTER);
        add(pInferior, BorderLayout.SOUTH);

        // Listeners
        chSolicitudes.addItemListener(e -> cargarSolicitud());
        btnGuardar.addActionListener(e -> guardarCambios());
        //btnAgregarProducto.addActionListener(this::actionPerformed);

        cargarSolicitud(); // Cargar primera solicitud
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void cargarSolicitud() {
        int idSeleccionado = Integer.parseInt(chSolicitudes.getSelectedItem());
        for (SolicitudCompra s : solicitudes) {
            if (s.getId() == idSeleccionado) {
                solicitudActual = s;
                break;
            }
        }

        chEstado.select(solicitudActual.getEstado().name());

        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(solicitudActual.getId()).append("\n");
        sb.append("Departamento: ").append(solicitudActual.getDepartamento().getNombre()).append("\n");
        sb.append("Estado: ").append(solicitudActual.getEstado()).append("\n\n");
        sb.append("Productos:\n");

        for (DetalleCompra d : solicitudActual.getProductos()) {
            Producto p = d.getProducto();
            sb.append("- ").append(p.getNombre())
                    .append(", Cantidad: ").append(d.getCantidad())
                    .append(", Precio: $").append(p.getPrecio()).append("\n");
        }

        sb.append("\nSubtotal: $").append(solicitudActual.calcularSubtotal());
        sb.append("\nIVA: $").append(solicitudActual.calcularIVA());
        sb.append("\nTotal: $").append(solicitudActual.calcularTotal());

        areaDetalle.setText(sb.toString());
    }

    private void guardarCambios() {
        String nuevoEstado = chEstado.getSelectedItem();
        solicitudActual.setEstado(EstadoSolicitud.valueOf(nuevoEstado));
        cargarSolicitud();
        showMessage("Cambios guardados correctamente.");
    }

    private void showMessage(String mensaje) {
        Dialog d = new Dialog(this, "Mensaje", true);
        d.setLayout(new FlowLayout());
        d.add(new Label(mensaje));
        Button b = new Button("OK");
        b.addActionListener(e -> d.setVisible(false));
        d.add(b);
        d.setSize(250, 100);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    //private void actionPerformed(ActionEvent e) {
      //  if (solicitudActual != null) {
        //    new VentanaProducto(solicitudActual);
          //  cargarSolicitud();
        //}
    //}
}

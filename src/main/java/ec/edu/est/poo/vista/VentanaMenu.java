package ec.edu.est.poo.vista;

import ec.edu.est.poo.modelos.Producto;
import ec.edu.est.poo.modelos.SolicitudCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaMenu extends Frame implements ActionListener {
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;
    private Label titulo;
    private Button btnEmpleado;
    private Button btnProveedor;
    private Button btnSolicitud;
    private Button btnProducto;
    private Button btnSalir;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<SolicitudCompra> solicitudCompras = new ArrayList<>();

    public VentanaMenu() {
        setTitle("Gestión de Compras");
        setSize(400, 500);
        setLayout(new BorderLayout());
        setBackground(new Color(174, 214, 241));
        setLocationRelativeTo(null);

        Panel general = new Panel();
        general.setLayout(new BorderLayout());

        pSuperior = new Panel();
        pCentral = new Panel(new GridLayout(4, 4));
        pInferior = new Panel();

        btnEmpleado = new Button("Empleado");
        btnProveedor = new Button("Proveedor");
        btnSolicitud = new Button("Solicitud");
        btnProducto = new Button("Productos");
        btnSalir = new Button("Cerrar");

        btnSalir.addActionListener(this);

        titulo = new Label("GESTION DE COMPRAS", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        pSuperior.add(titulo);

        pCentral.add(btnEmpleado);
        pCentral.add(btnProveedor);
        pCentral.add(btnSolicitud);
        pCentral.add(btnProducto);

        pInferior.add(btnSalir);

        general.add(pSuperior, BorderLayout.NORTH);
        general.add(pCentral, BorderLayout.CENTER);
        general.add(pInferior, BorderLayout.SOUTH);

        add(general, BorderLayout.CENTER);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evento) {
                System.exit(0);
            }
        });

        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEmpleado ventanaEmpleado = new VentanaEmpleado();
                ventanaEmpleado.setVisible(true);
            }
        });
        btnProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProveedor ventanaProveedor = new VentanaProveedor();
                ventanaProveedor.setVisible(true);
            }
        });
        btnSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListaSolicitud ventanaListaSolicitud = new VentanaListaSolicitud(solicitudCompras);
            }
        });
        btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProducto ventanaProducto = new VentanaProducto(listaProductos);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }
}
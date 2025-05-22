package ec.edu.est.poo.vista;

import ec.edu.est.poo.modelos.Departamento;
import ec.edu.est.poo.modelos.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaEmpleado extends Frame implements ActionListener {
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;
    private Panel pAgregar;
    private Panel pListado;

    private Button btnAgregar;
    private Button btnGuardar;
    private Button btnListar;

    private Label titulo;
    private Label lbId;
    private Label lbNombre;
    private Label lbDireccion;
    private Label lbTelefono;
    private Label lbCargo;
    private Label lbIdDep;
    private Label lbDepartamento;

    private TextArea txtMostrar;

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtCargo;
    private TextField txtIdDep;
    private TextField txtDepartamento;

    private CardLayout cardLayout;
    private List<Empleado> listaEmpleados = new ArrayList<>();

    public VentanaEmpleado() {
        setTitle("Menú de Empleados");
        setSize(500, 600);
        setLayout(new BorderLayout());
        setBackground(new Color(174, 214, 241));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Panel pGeneral = new Panel();

        cardLayout = new CardLayout();

        pSuperior = new Panel();
        pCentral = new Panel(new FlowLayout(FlowLayout.CENTER));
        pAgregar = new Panel(new GridLayout(8, 2, 10, 15));
        pListado = new Panel(new BorderLayout());
        pInferior = new Panel(cardLayout);

        pSuperior.setBackground(new Color(247, 249, 249));

        titulo = new Label("MENU DE EMPLEADOS", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar Empleado");
        btnListar = new Button("Listar Empleados");
        btnGuardar = new Button("Guardar");

        lbId = new Label("Id: ");
        lbNombre = new Label("Nombre: ");
        lbDireccion = new Label("Dirección: ");
        lbTelefono = new Label("Teléfono: ");
        lbCargo = new Label("Cargo: ");
        lbIdDep = new Label("Id Departamento: ");
        lbDepartamento = new Label("Departamento: ");

        txtId = new TextField(15);
        txtNombre = new TextField(15);
        txtDireccion = new TextField(15);
        txtTelefono = new TextField(15);
        txtCargo = new TextField(15);
        txtIdDep = new TextField(15);
        txtDepartamento = new TextField(15);

        txtMostrar = new TextArea("Aquí se mostrará la lista de empleados...", 5, 30);
        txtMostrar.setEditable(false);

        pListado.add(new Label("Aquí se mostrará a los empleados", Label.CENTER));
        pListado.add(txtMostrar, BorderLayout.CENTER);

        pCentral.add(btnAgregar);
        pCentral.add(btnListar);

        pSuperior.add(titulo);

        pAgregar.add(lbId);
        pAgregar.add(txtId);
        pAgregar.add(lbNombre);
        pAgregar.add(txtNombre);
        pAgregar.add(lbDireccion);
        pAgregar.add(txtDireccion);
        pAgregar.add(lbTelefono);
        pAgregar.add(txtTelefono);
        pAgregar.add(lbCargo);
        pAgregar.add(txtCargo);
        pAgregar.add(lbIdDep);
        pAgregar.add(txtIdDep);
        pAgregar.add(lbDepartamento);
        pAgregar.add(txtDepartamento);
        pAgregar.add(btnGuardar);
        pAgregar.add(new Label(""));

        pInferior.add(pAgregar, "Agregar");
        pInferior.add(pListado, "Listado");

        pGeneral.add(pSuperior, BorderLayout.NORTH);
        pGeneral.add(pCentral, BorderLayout.CENTER);
        pGeneral.add(pInferior, BorderLayout.SOUTH);

        add(pGeneral, BorderLayout.CENTER);

        setVisible(true);

        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);
        btnGuardar.addActionListener(this);

        cardLayout.show(pInferior, "Formulario");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            cardLayout.show(pInferior, "Agregar");
            limpiarFormulario();
        } else if (e.getSource() == btnListar) {
            cardLayout.show(pInferior, "Listado");
            mostrarLista();
        } else if (e.getSource() == btnGuardar) {
            guardarEmpleado();
        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCargo.setText("");
        txtIdDep.setText("");
        txtDepartamento.setText("");
    }

    private void mostrarLista() {
        StringBuilder sb = new StringBuilder();
        if (listaEmpleados.isEmpty()) {
            sb.append("No hay empleados...");
        } else {
            for (Empleado empleado : listaEmpleados) {
                sb.append(empleado.toString());
                sb.append("\n");
            }
            txtMostrar.setText(sb.toString());
        }
    }

    private void guardarEmpleado() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String cargo = txtCargo.getText();
            int idDep = Integer.parseInt(txtIdDep.getText());
            String departamentoNombre = txtDepartamento.getText();
            Departamento departamento = new Departamento(idDep, departamentoNombre);

            Empleado nuevoEmpleado = new Empleado(id, nombre, direccion, telefono, cargo, departamento);
            listaEmpleados.add(nuevoEmpleado);
            JOptionPane.showMessageDialog(this, "Empleado guardado con éxito.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            cardLayout.show(pInferior, "Listado");
            mostrarLista();

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Error: Ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
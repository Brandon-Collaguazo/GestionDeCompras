package ec.edu.est.poo.controlador;

import ec.edu.est.poo.modelos.EstadoSolicitud;
import ec.edu.est.poo.modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private List<Empleado> empleados = new ArrayList<>();
    private List<Proveedor> proveedores = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<SolicitudCompra> solicitudes = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();

    public int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    public void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE COMPRAS ERP ---");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Registrar empleado");
            System.out.println("3. Registrar producto");
            System.out.println("4. Registrar solicitud de compra");
            System.out.println("5. Listar proveedores");
            System.out.println("6. Listar empleados");
            System.out.println("7. Listar productos");
            System.out.println("8. Listar solicitudes de compra");
            System.out.println("9. Buscar proveedor por ID");
            System.out.println("10. Buscar empleado por nombre");
            System.out.println("11. Buscar producto por nombre");
            System.out.println("12. Buscar solicitud por número");
            System.out.println("13. Aprobar / Rechazar solicitud de compra");
            System.out.println("14. Calcular total de una solicitud");
            System.out.println("15. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> registrarProveedor();
                case 2 -> registrarEmpleado();
                case 3 -> registrarProducto();
                case 4 -> registrarSolicitud();
                case 5 -> listarProveedores();
                case 6 -> listarEmpleados();
                case 7 -> listarProductos();
                case 8 -> listarSolicitudes();
                case 9 -> buscarProveedorPorID();
                case 10 -> buscarEmpleadoPorNombre();
                case 11 -> buscarProductoPorNombre();
                case 12 -> buscarSolicitudPorNumero();
                case 13 -> aprobarRechazarSolicitud();
                case 14 -> calcularTotalSolicitud();
                case 15 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 15);
    }

    public void registrarProveedor() {
        System.out.print("ID: ");
        int id = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.println("Productos ofrecidos por el proveedor:");
        Producto producto = crearProducto();

        Proveedor proveedor = new Proveedor(id, nombre, direccion, telefono, producto);
        proveedor.registrarProducto(producto.getCodigo(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
        proveedores.add(proveedor);
        System.out.println("Proveedor registrado exitosamente.");
    }

    public void registrarProducto() {
        Producto producto = crearProducto();
        productos.add(producto);
        System.out.println("Producto registrado exitosamente.");
    }

    public Producto crearProducto() {
        System.out.print("Código: ");
        int codigo = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        return new Producto(codigo, nombre, descripcion, precio);
    }

    public void registrarEmpleado() {
        System.out.print("ID: ");
        int id = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Id del departamento: ");
        int depId = leerEntero();

        Departamento departamento = new Departamento(depId, "XD");
        Empleado empleado = new Empleado(id, nombre, direccion, telefono, cargo, departamento);
        empleados.add(empleado);
        System.out.println("Empleado registrado exitosamente.");
    }

    public void registrarSolicitud() {
        System.out.print("ID de la solicitud: ");
        int id = leerEntero();
        System.out.print("Departamento solicitante: ");
        String departamento = scanner.nextLine();
        EstadoSolicitud estado = EstadoSolicitud.EN_REVISION;
        List<DetalleCompra> detalles = new ArrayList<>();
        String continuar;
        do {
            System.out.println("\nAñadir producto a la solicitud:");
            Producto producto = crearProducto();
            System.out.print("Cantidad: ");
            int cantidad = leerEntero();
            detalles.add(new DetalleCompra(producto, cantidad));
            System.out.print("¿Desea agregar otro producto? (S/N): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("S"));

        SolicitudCompra solicitud = new SolicitudCompra(id, departamento, estado, detalles);
        solicitudes.add(solicitud);
        System.out.println("Solicitud registrada exitosamente.");
    }

    public void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("Lista de Proveedores");
            for (Proveedor proveedor : proveedores) {
                proveedor.toString();
                System.out.println();
            }
        }
    }

    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("Lista de empleados");
                empleado.toString();
                System.out.println();
            }
        }
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
                System.out.println();
            }
        }
    }

    public void listarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            System.out.println("Lista de solicitudes");
            for (SolicitudCompra solicitudCompra : solicitudes) {
                System.out.println(solicitudCompra);
                System.out.println();
            }
        }
    }

    public void buscarProveedorPorID() {
        System.out.print("Ingrese el ID del proveedor a buscar: ");
        String id = scanner.nextLine();
        boolean find = false;
        for(Proveedor proveedor : proveedores) {
            if(proveedor.coincideCon(id)) {
                System.out.println("Proveedor encontrado: " + proveedor);
                find = true;
                break;
            }
        }
        if(!find) {
            System.out.println("Proveedor no encontrado");
        }
    }

    public void buscarEmpleadoPorNombre() {
        System.out.print("Ingrese el nombre del empleado a buscar: ");
        String nombre = scanner.nextLine();
        boolean find = false;
        for(Empleado empleado : empleados) {
            if(empleado.coincideCon(nombre)) {
                System.out.println("Empleado encontrado: " + empleado);
                find = true;
                break;
            }
        }
        if(!find) {
            System.out.println("Empleado no encontrado");
        }
    }

    public void buscarProductoPorNombre() {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombre = scanner.nextLine();
        boolean find = false;
        for(Producto producto : productos) {
            if(producto.coincideCon(nombre)) {
                System.out.println("Producto encontrado: " + producto);
                find = true;
                break;
            }
        }
        if(!find) {
            System.out.println("Producto no encontrado");
        }
    }

    public SolicitudCompra buscarSolicitudPorNumero() {
        System.out.print("Ingrese el número de solicitud a buscar: ");
        int id = leerEntero();
        String idString = String.valueOf(id);
        for (SolicitudCompra solicitudCompra : solicitudes) {
            if (solicitudCompra.coincideCon(idString)) {
                System.out.println("Solicitud encontrada: " + solicitudCompra);
                return solicitudCompra;
            }
        }
        System.out.println("Solicitud no encontrada");
        return null;
    }

    public void aprobarRechazarSolicitud() {
        SolicitudCompra solicitudFind = buscarSolicitudPorNumero();
        if(solicitudFind != null) {
            System.out.print("¿Desea aprobar(A) o rechazar(R) la solicitud? ");
            String opcion = scanner.nextLine().toUpperCase();
            if(opcion.equalsIgnoreCase("A")) {
                solicitudFind.aceptarSolicitud();
            } else if (opcion.equalsIgnoreCase("R")) {
                solicitudFind.rechazarSolicitud();
            } else {
                System.out.println("Opción inválida");
            }
        } else {
            System.out.println("Solicitud no encontrada");
        }
    }

    public void calcularTotalSolicitud() {
        SolicitudCompra solicitudFind = buscarSolicitudPorNumero();
        if(solicitudFind != null) {
            double total = solicitudFind.calcularTotal();
            System.out.println("El total de la solicitud es: " + total);
        } else {
            System.out.println("Solicitud no encontrada");
        }
    }
}

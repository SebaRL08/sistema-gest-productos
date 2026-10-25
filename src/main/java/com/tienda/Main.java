
package com.tienda;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.tienda.dao.productoDAO;
import com.tienda.modelo.producto;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final productoDAO dao = new productoDAO();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n===== TIENDA: GESTION DE PRODUCTOS =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1: registrar(); break;
                    case 2: listar(); break;
                    case 3: buscar(); break;
                    case 4: actualizar(); break;
                    case 5: eliminar(); break;
                    case 0: System.out.println("Programa finalizado."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (NumberFormatException e) {
                opcion = -1;
                System.out.println("Ingrese un numero valido.");
            } catch (SQLException e) {
                opcion = -1;
                System.out.println("Error de base de datos: " + e.getMessage());
            }
        } while (opcion != 0);

        teclado.close();
    }

    private static void registrar() throws SQLException {
        System.out.println("\n--- REGISTRAR PRODUCTO ---");

        System.out.print("Codigo: ");
        String codigo = teclado.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine().trim();

        System.out.print("Descripcion: ");
        String descripcion = teclado.nextLine().trim();

        BigDecimal precio = leerPrecio();
        int cantidad = leerCantidad();

        if (codigo.isEmpty() || nombre.isEmpty()) {
            System.out.println("El codigo y el nombre son obligatorios.");
            return;
        }

        producto p = new producto(
                codigo, nombre, descripcion, precio, cantidad);

        if (dao.insertar(p)) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el producto.");
        }
    }

    private static void listar() throws SQLException {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");

        List<producto> productos = dao.listar();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (producto p : productos) {
                System.out.println(p);
            }
        }
    }

    private static void buscar() throws SQLException {
        System.out.print("Codigo del producto: ");
        String codigo = teclado.nextLine().trim();

        producto p = dao.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("Producto no encontrado.");
        } else {
            System.out.println(p);
        }
    }

    private static void actualizar() throws SQLException {
        System.out.print("Codigo del producto a actualizar: ");
        String codigo = teclado.nextLine().trim();

        producto existente = dao.buscarPorCodigo(codigo);

        if (existente == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Deja un campo vacio para conservar su valor.");

        System.out.print("Nuevo nombre [" + existente.getNombre() + "]: ");
        String nombre = teclado.nextLine().trim();
        if (!nombre.isEmpty()) {
            existente.setNombre(nombre);
        }

        System.out.print("Nueva descripcion [" + existente.getDescripcion() + "]: ");
        String descripcion = teclado.nextLine().trim();
        if (!descripcion.isEmpty()) {
            existente.setDescripcion(descripcion);
        }

        System.out.print("Nuevo precio [" + existente.getPrecio() + "]: ");
        String precioTexto = teclado.nextLine().trim();
        if (!precioTexto.isEmpty()) {
            BigDecimal precio = convertirPrecio(precioTexto);
            if (precio == null) {
                System.out.println("Precio no valido. No se actualizo.");
                return;
            }
            existente.setPrecio(precio);
        }

        System.out.print("Nueva cantidad [" + existente.getCantidad() + "]: ");
        String cantidadTexto = teclado.nextLine().trim();
        if (!cantidadTexto.isEmpty()) {
            try {
                int cantidad = Integer.parseInt(cantidadTexto);
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa.");
                    return;
                }
                existente.setCantidad(cantidad);
            } catch (NumberFormatException e) {
                System.out.println("Cantidad no valida. No se actualizo.");
                return;
            }
        }

        if (dao.actualizar(existente)) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el producto.");
        }
    }

    private static void eliminar() throws SQLException {
        System.out.print("Codigo del producto a eliminar: ");
        String codigo = teclado.nextLine().trim();

        producto existente = dao.buscarPorCodigo(codigo);

        if (existente == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println(existente);
        System.out.print("¿Seguro que desea eliminarlo? (S/N): ");
        String confirmar = teclado.nextLine().trim();

        if (confirmar.equalsIgnoreCase("S")) {
            if (dao.eliminar(codigo)) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }
        } else {
            System.out.println("Eliminacion cancelada.");
        }
    }

    private static BigDecimal leerPrecio() {
        while (true) {
            System.out.print("Precio: ");
            BigDecimal precio = convertirPrecio(teclado.nextLine().trim());

            if (precio != null) {
                return precio;
            }

            System.out.println("Ingrese un precio valido y no negativo.");
        }
    }

    private static BigDecimal convertirPrecio(String texto) {
        try {
            BigDecimal precio = new BigDecimal(texto.replace(",", "."));

            if (precio.scale() > 2) {
                return null;
            }

            if (precio.compareTo(BigDecimal.ZERO) < 0) {
                return null;
            }

            return precio;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static int leerCantidad() {
        while (true) {
            System.out.print("Cantidad: ");

            try {
                int cantidad = Integer.parseInt(teclado.nextLine().trim());

                if (cantidad >= 0) {
                    return cantidad;
                }

                System.out.println("La cantidad no puede ser negativa.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }
}

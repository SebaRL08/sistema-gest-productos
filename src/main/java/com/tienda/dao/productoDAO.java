
package com.tienda.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tienda.conexion.conexion;
import com.tienda.modelo.producto;

public class productoDAO {

    // Registrar un producto
    public boolean insertar(producto producto) throws SQLException {
        String sql = "INSERT INTO productos "
                + "(codigo, nombre, descripcion, precio, cantidad) "
                + "VALUES (?, ?, ?, ?, ?)";

    try (Connection conexionDB = conexion.obtenerConexion();
     PreparedStatement ps = conexionDB.prepareStatement(sql)) {

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setBigDecimal(4, producto.getPrecio());
            ps.setInt(5, producto.getCantidad());

            return ps.executeUpdate() > 0;
        }
    }

    // Listar todos los productos
    public List<producto> listar() throws SQLException {
        List<producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY codigo";

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement ps = conexionDB.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                producto producto = new producto(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getBigDecimal("precio"),
                        rs.getInt("cantidad")
                );

                productos.add(producto);
            }
        }

        return productos;
    }

    // Buscar un producto por su codigo
    public producto buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM productos WHERE codigo = ?";

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement ps = conexionDB.prepareStatement(sql)) {

            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new producto(
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getBigDecimal("precio"),
                            rs.getInt("cantidad")
                    );
                }
            }
        }

        return null;
    }

    // Actualizar un producto existente
    public boolean actualizar(producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, "
                + "precio = ?, cantidad = ? WHERE codigo = ?";

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement ps = conexionDB.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setString(5, producto.getCodigo());

            return ps.executeUpdate() > 0;
        }
    }

    // Eliminar un producto por su codigo
    public boolean eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM productos WHERE codigo = ?";

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement ps = conexionDB.prepareStatement(sql)) {

            ps.setString(1, codigo);

            return ps.executeUpdate() > 0;
        }
    }
}

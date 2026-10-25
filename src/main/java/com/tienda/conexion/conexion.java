
package com.tienda.conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class conexion {

    private static final Properties propiedades = new Properties();

    static {
        Path archivo = Paths.get("db.properties");

        try (InputStream entrada = Files.exists(archivo)
                ? new FileInputStream(archivo.toFile())
                : conexion.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {

            if (entrada == null) {
                throw new IllegalStateException(
                    "No se encontro db.properties. " +
                    "Colocalo junto al archivo JAR.");
            }

            propiedades.load(entrada);

        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection obtenerConexion() throws SQLException {
        String url = propiedades.getProperty("db.url");
        String usuario = propiedades.getProperty("db.user");
        String contrasena = propiedades.getProperty("db.password");

        return DriverManager.getConnection(url, usuario, contrasena);
    }
}

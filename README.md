# 🛒 Sistema de Gestión de Productos

Aplicación de escritorio por consola desarrollada en Java para administrar un inventario de productos mediante una base de datos MySQL. El proyecto aplica operaciones CRUD y separación de responsabilidades para facilitar su mantenimiento.

## 📌 Características

- **Registrar:** agregar nuevos productos al inventario.
- **Consultar:** visualizar los productos registrados.
- **Actualizar:** modificar la información de los productos.
- **Eliminar:** retirar productos del inventario.
- **Persistencia de datos:** la información se almacena en MySQL.
- **Validación de datos:** control de valores para evitar precios y cantidades negativos.
- **Acceso a datos:** uso de JDBC y consultas preparadas (`PreparedStatement`).

## 🧰 Tecnologías utilizadas

- Java
- Maven
- MySQL
- JDBC
- Git y GitHub
- Visual Studio Code (opcional, para desarrollo)

## 📋 Requisitos

Para ejecutar el proyecto se necesita:

- Java JDK instalado.
- MySQL Server en funcionamiento.
- Maven, si se desea compilar el código fuente.
- Una base de datos configurada según las instrucciones de este documento.

No es necesario abrir Visual Studio Code para ejecutar el archivo JAR una vez compilado.

## 🗄️ Configuración de la base de datos

Abre MySQL Workbench o la consola de MySQL y ejecuta el siguiente script:

```sql
CREATE DATABASE IF NOT EXISTS tienda_db;
USE tienda_db;

CREATE TABLE IF NOT EXISTS productos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL,
    CONSTRAINT chk_precio CHECK (precio >= 0),
    CONSTRAINT chk_cantidad CHECK (cantidad >= 0)
);
```

Este script crea la base de datos `tienda_db` y la tabla `productos`.

## 🔐 Configuración de conexión

Por seguridad, las credenciales de MySQL se configuran localmente y no deben publicarse en GitHub.

En la carpeta principal del proyecto, crea un archivo llamado `db.properties`, al mismo nivel que `pom.xml`, con el siguiente contenido:

```properties
db.url=jdbc:mysql://localhost:3306/tienda_db
db.user=root
db.password=TU_CONTRASENA
```

Reemplaza `TU_CONTRASENA` por la contraseña de tu instalación de MySQL.

**Importante:** no compartas este archivo ni subas tus contraseñas al repositorio. El archivo `db.properties` está excluido mediante `.gitignore`.

## ▶️ Compilar y ejecutar

### Opción 1: Compilar el proyecto

Abre una terminal en la carpeta que contiene `pom.xml` y ejecuta:

```bash
mvn clean package
```

Cuando la compilación termine correctamente, el archivo JAR se generará en:

```text
target/sistema-gest-productos-1.0-SNAPSHOT.jar
```

### Opción 2: Ejecutar el JAR

Desde la carpeta principal del proyecto, ejecuta:

```bash
java -jar target/sistema-gest-productos-1.0-SNAPSHOT.jar
```

También puedes ejecutar el archivo `ejecutar.bat` con doble clic en Windows, después de compilar el proyecto.

Antes de ejecutar el programa, verifica que MySQL esté iniciado y que `db.properties` tenga los datos de conexión correctos.

## 📁 Estructura del proyecto

```text
sistema-gest-productos/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/tienda/
│   │           ├── Main.java
│   │           ├── conexion/
│   │           │   └── conexion.java
│   │           ├── modelo/
│   │           └── dao/
│   └── test/
├── db.properties          # Configuración local, no publicada
├── ejecutar.bat
├── .gitignore
├── pom.xml
└── README.md
```

## 🧱 Arquitectura

El proyecto organiza el código por responsabilidades:

- **Main:** presenta el menú y gestiona la interacción con el usuario.
- **modelo:** representa la información de los productos.
- **dao:** contiene las operaciones de acceso y gestión de los datos.
- **conexion:** administra la conexión con la base de datos MySQL.

Esta separación facilita el mantenimiento y la comprensión del programa.

## 🔒 Seguridad

- Las credenciales locales se excluyen del repositorio mediante `.gitignore`.
- Las consultas preparadas ayudan a reducir el riesgo de inyección SQL.
- No publiques contraseñas ni archivos de configuración privados.

## 👨‍💻 Autoría y propósito académico

Proyecto académico desarrollado para practicar persistencia de datos en Java, conexión con MySQL, operaciones CRUD y organización del código.


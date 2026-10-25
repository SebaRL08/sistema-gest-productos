# Versión web — Sistema de Gestión de Productos

Esta carpeta contiene una interfaz web mínima para administrar productos con Supabase como base de datos en la nube. La versión Java original se conserva por separado.

## Funciones
- Registrar, consultar, buscar, editar y eliminar productos.
- Validar precio y cantidad no negativos.
- Interfaz adaptable a escritorio y móvil.

## Publicación rápida

### 1. Crear la base de datos
1. Crea un proyecto en https://supabase.com/.
2. Abre **SQL Editor** y ejecuta todo el contenido de `supabase.sql`.
3. En **Project Settings / API** (o Connect), copia la Project URL y la clave pública/publishable (anon key). Nunca uses `service_role` ni una clave secreta en el navegador.

### 2. Configurar la web
Abre `public/index.html` y reemplaza:
- `PEGA_AQUI_TU_PROJECT_URL` por la URL del proyecto.
- `PEGA_AQUI_TU_PUBLISHABLE_OR_ANON_KEY` por la clave pública/anon.

Guarda el archivo.

### 3. Publicar
Sube la carpeta `web` al repositorio GitHub. En Netlify, crea un sitio nuevo importando ese repositorio y establece:
- **Base directory:** `web`
- **Publish directory:** `public`
- **Build command:** dejar vacío

Si Netlify no permite seleccionar el directorio raíz publicado, configura el sitio para publicar `web/public`. Después de desplegar, Netlify te dará una URL pública.

## Importante sobre seguridad
Esta es una demo académica sin inicio de sesión. Las políticas de `supabase.sql` permiten a cualquier visitante del enlace leer y modificar los datos de la tabla. No guardes información sensible. Para un sistema real, añade autenticación y políticas por usuario.

La clave `anon`/publishable está diseñada para uso público en el navegador cuando RLS y las políticas están correctamente configuradas. Nunca pongas la clave `service_role` o una clave secreta en `index.html`.

## Prueba local
Puedes abrir `public/index.html` en el navegador después de configurar Supabase y tener internet. Para publicar, usa los pasos anteriores.

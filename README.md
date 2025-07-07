# paises
# 🌍 Consulta de Países con RESTCountries API

Este proyecto es una aplicación Java que permite consultar información de países a través de la [API RESTCountries](https://restcountries.com/), y guardar los datos consultados en una base de datos. Incluye funciones para filtrar, listar y analizar los países almacenados.

---

## ✨ Características

- Consultar un país por nombre exacto desde la API.
- Guardar información del país en una base de datos relacional.
- Mostrar todos los países consultados.
- Consultar país directamente desde la base de datos.
- Mostrar los 5 países más poblados.
- Listar nombres oficiales de los países.
- Listar idiomas únicos registrados.
- Mostrar las monedas registradas por país.
- Mostrar conteo de países por región.
- Listar países ordenados por área (de mayor a menor).

---

## 🛠️ Herramientas y Tecnologías Usadas

- **Java 17**
- **Maven** – Para la gestión del proyecto y dependencias.
- **Spring Boot** – Framework principal para la aplicación.
- **Spring Data JPA** – Para la interacción con la base de datos.
- **Hibernate** – Implementación de JPA.
- **H2 Database** (o PostgreSQL, según configuración) – Base de datos para persistencia.
- **RESTCountries API** – Fuente de datos para los países (`https://restcountries.com/v3.1/name/{nombre}?fullText=true`)
- **Jackson / Gson** – Para convertir JSON a objetos Java (DTO).
- **Lombok** – (Opcional) Para simplificar getters, setters y constructores.
- **Scanner** – Para interacción por consola.

---

## 📁 Estructura del Proyecto

```
com.aluracursos.paises
│
├── principal
│   └── Principal.java         // Contiene el menú e interacción principal
│
├── clase
│   ├── Pais.java              // Entidad País
│   └── Moneda.java            // Clase auxiliar para representar monedas
│
├── model
│   └── DatosPais.java         // Record/DTO para mapear la respuesta de la API
│
├── repository
│   └── PaisRepository.java    // Interfaz JPA para acceso a la base de datos
│
├── service
│   ├── ConsumoApi.java        // Clase para consumir la API externa
│   └── ConvierteDatos.java    // Conversión de JSON a objetos Java
```

---

## 🔄 Flujo de Ejecución

1. El usuario ejecuta el programa y se le presenta un menú por consola.
2. Al elegir buscar un país por nombre, se consulta la API `https://restcountries.com`.
3. Se convierte la respuesta JSON en objetos Java usando `ConvierteDatos`.
4. Se verifica si ya existe el país en la base de datos.
5. Si no existe, se guarda en la base de datos utilizando `PaisRepository`.
6. El usuario puede realizar diversas consultas sobre los datos guardados.

---

## ▶️ Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/nombre-proyecto.git
   ```

2. Entra al directorio del proyecto:
   ```bash
   cd nombre-proyecto
   ```

3. Compila el proyecto con Maven:
   ```bash
   mvn clean install
   ```

4. Ejecuta la clase `Principal.java` desde tu IDE o desde consola si tienes configurado Maven:
   ```bash
   mvn exec:java -Dexec.mainClass="com.aluracursos.paises.principal.Principal"
   ```

---

## 📸 Ejemplo de menú

```
==============================================================
🌍           MENÚ DE CONSULTA DE PAÍSES           🌍
==============================================================
1 - Buscar país por nombre exacto (desde la Web)
2 - Mostrar todos los Paises buscados
3 - Buscar país por su nombre exacto (desde la Base de datos)
4 - Mostrar los 5 países más poblados
5 - Mostrar los países registrados por su nombre oficial
6 - Mostrar los idiomas registrados
7 - Mostrar las monedas registradas
8 - Mostrar la cantidad de países por región
9 - Mostrar países con mayor área de mayor a menor
0 - Salir
--------------------------------------------------------------
Elija una opción:
```

---

## 📦 Dependencias Maven sugeridas

Agrega estas dependencias al `pom.xml` si aún no están:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- O PostgreSQL si usas otro gestor -->
    <!--
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    -->

    <!-- Optional: Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## 📌 Notas

- El sistema solo guarda el país si no se encuentra previamente en la base de datos.
- Se recomienda manejar mejor los errores HTTP y validaciones en producción.
- Puedes ampliar el sistema para permitir búsquedas por continente, capital o código ISO.

---

## 📄 Licencia

Proyecto con fines educativos – Curso Alura.

---

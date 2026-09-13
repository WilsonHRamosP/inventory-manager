# 📦 Inventory Manager

Sistema web de gestión de inventario desarrollado con **Java 21 y Spring Boot**, con persistencia en **PostgreSQL**, interfaz web utilizando **Thymeleaf** y autenticación mediante **Spring Security**.

El proyecto fue desarrollado como aplicación de portafolio, aplicando conceptos de desarrollo backend, arquitectura MVC, persistencia de datos, validaciones, seguridad y despliegue en la nube.

## 🚀 Demo

🌐 **Aplicación desplegada:**  
https://inventory-manager-tnax.onrender.com

> La aplicación está desplegada en Render y utiliza PostgreSQL como base de datos.

---

## 📋 Descripción

Inventory Manager permite administrar un inventario y registrar las ventas realizadas sobre los productos disponibles.

El sistema cuenta con diferentes niveles de acceso mediante autenticación:

- **ADMIN:** administración completa del sistema.
- **USER:** consulta del inventario y registro de ventas.

---

## ✨ Funcionalidades

### 📦 Productos

- Crear productos.
- Editar productos.
- Eliminar productos.
- Consultar productos.
- Control de stock.
- Asociación de productos con categorías.
- Validación de datos.
- Control de productos con stock bajo.

### 🗂️ Categorías

- Crear categorías.
- Editar categorías.
- Eliminar categorías.
- Consultar categorías.

### 💰 Ventas

- Registrar ventas.
- Consultar ventas.
- Consultar detalle de una venta.
- Actualizar ventas.
- Control automático del stock.
- Cálculo automático de subtotales y total.
- Validación de disponibilidad de productos.

### 📊 Dashboard

El sistema presenta información general del inventario:

- Total de productos.
- Total de categorías.
- Total de ventas.
- Ingresos totales.
- Productos con stock bajo.

### 🔐 Seguridad

Implementación de autenticación y autorización utilizando **Spring Security**.

- Login.
- Logout.
- Roles `ADMIN` y `USER`.
- Protección de rutas.
- Restricción de operaciones según rol.
- Contraseñas gestionadas mediante Spring Security.
- Página de acceso denegado.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| ☕ Java 21 | Lenguaje principal |
| 🌱 Spring Boot | Framework principal |
| 🔐 Spring Security | Autenticación y autorización |
| 🗃️ Spring Data JPA | Persistencia |
| 🐘 PostgreSQL | Base de datos |
| 🧩 Hibernate | ORM |
| 🎨 Thymeleaf | Vistas web |
| 🧱 HTML / CSS | Interfaz |
| 📦 Maven | Gestión de dependencias |
| 🐙 Git / GitHub | Control de versiones |
| 🚀 Render | Despliegue |

---

## 🏗️ Arquitectura

El proyecto utiliza una estructura basada en capas:

src/main/java/com/wildev/inventory
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
└── service/impl

La aplicación sigue principalmente el patrón **MVC (Model-View-Controller)** y utiliza una separación por responsabilidades entre controladores, servicios y repositorios.

---

## 🗄️ Modelo de datos

Las principales entidades del sistema son:

User
 │
 └── Seguridad y roles

Category
 │
 └── Product
       │
       └── SaleItem
              │
              └── Sale

Las relaciones principales incluyen:

- Una categoría puede tener múltiples productos.
- Una venta puede contener múltiples elementos de venta.
- Cada elemento de venta está asociado a un producto.
- El stock se actualiza automáticamente al registrar o modificar ventas.

---

## 🔑 Usuarios de prueba

El sistema crea usuarios iniciales mediante un `DataInitializer`.

### ADMIN

Usuario: admin
Rol: ADMIN

### USER

Rol: USER

> Las credenciales de producción no se almacenan en el repositorio. Las variables sensibles se configuran mediante variables de entorno en Render.

---

## ⚙️ Ejecución local

### 1. Clonar el repositorio

```bash
git clone https://github.com/WilsonHRamosP/inventory-manager.git
```

```bash
cd inventory-manager
```
```

### 2. Configurar PostgreSQL

Crear una base de datos PostgreSQL, por ejemplo:

```text
inventory_db
```

Configurar las credenciales correspondientes en `application.properties`.

### 3. Ejecutar la aplicación

En Windows:

```bash
.\mvnw spring-boot:run
```

O utilizando Maven:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8085
```

---

## 🌐 Despliegue

La aplicación está desplegada en **Render** utilizando:

```text
GitHub
   ↓
Render Web Service
   ↓
Spring Boot
   ↓
PostgreSQL
```

Las credenciales y datos de conexión de producción se manejan mediante variables de entorno.

Variables utilizadas:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
SPRING_PROFILES_ACTIVE
```

El perfil de producción utilizado es:

```text
prod
```

---

## 📈 Roadmap del proyecto

### Sprint 0 — Planeación
✅ Completado

### Sprint 1 — Base del proyecto
✅ Completado

### Sprint 2 — Categorías
✅ Completado

### Sprint 3 — Productos
✅ Completado

### Sprint 4 — Ventas
✅ Completado

### Sprint 5 — Dashboard y vistas
✅ Completado

### Sprint 6 — Seguridad
✅ Completado

### Sprint 7 — Deploy
✅ Completado

---

## 🎯 Objetivo del proyecto

Este proyecto fue desarrollado con el objetivo de construir una aplicación completa que permita demostrar conocimientos prácticos en:

- Java.
- Programación orientada a objetos.
- Spring Boot.
- Spring MVC.
- Spring Data JPA.
- Hibernate.
- PostgreSQL.
- Spring Security.
- Thymeleaf.
- Validaciones.
- Git y GitHub.
- Despliegue de aplicaciones.
- Manejo de variables de entorno.
- Arquitectura por capas.

---

## 👨‍💻 Autor

**Wilson Ramos**

GitHub:  
https://github.com/WilsonHRamosP

Repositorio:  
https://github.com/WilsonHRamosP/inventory-manager

---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos y de portafolio.

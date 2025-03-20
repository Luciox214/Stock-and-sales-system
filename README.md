# 📦 Sistema de Gestión de Stock y Ventas (Backend)

🚀 **Sistema desarrollado en Spring Boot** para la gestión de productos y ventas, diseñado para pequeños negocios que necesitan un control eficiente de su stock.

---

## 📌 Características principales  
✅ **Gestión de Productos**: Crear, editar, eliminar y consultar productos.  
✅ **Registro de Ventas**: Registrar ventas con múltiples productos y actualizar el stock automáticamente.  
✅ **API REST** con endpoints bien definidos para interactuar con el sistema.  
✅ **Persistencia de datos** con **Hibernate** y **MySQL**.  
✅ **Arquitectura basada en MVC** (Controllers, Services y Repositories).  

---

## 🛠️ Tecnologías utilizadas  
| Tecnología  | Descripción |
|------------|------------|
| **Java 17** | Lenguaje de programación principal |
| **Spring Boot** | Framework para el backend |
| **MySQL** | Base de datos relacional (gestionada con XAMPP) |
| **Hibernate** | ORM para la gestión de la base de datos |
| **Maven** | Gestión de dependencias |

---

## 🚀 Instalación y ejecución  

### 🔹 1. Clonar el repositorio  

git clone https://github.com/Luciox214/Stock-and-sales-system.git


 ### 🔹 2. Configurar la base de datos
Para que el sistema funcione correctamente, sigue estos pasos:

1. Inicia MySQL en XAMPP.
2. Crea una base de datos con el nombre `gestion_stock`.
3. *(Opcional)* Importa el archivo `database.sql` si lo incluyes en el repositorio.
4. Configura las credenciales en el archivo `src/main/resources/application.properties`:

## 3. Ejecutar el backend
Ejecuta el siguiente comando en la terminal dentro del proyecto:

```bash
mvn spring-boot:run


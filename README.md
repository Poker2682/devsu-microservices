# Prueba tecnica DEVSU

### Datos de Contacto:

**Daniel Montes**  
[daniel.montes2682@gmail.com](mailto:daniel.montes2682@gmail.com)  
+57 300 2885315  
[LinkedIn](https://www.linkedin.com/in/daniel-montes-68a7901b2/)


## Resumen

Este proyecto consta de dos microservicios y una base de datos MySQL, todos gestionados con Docker Compose.

- **Microservicio de Clientes y Personas**: Corre en el puerto 8080.
- **Microservicio de Cuentas y Transacciones**: Corre en el puerto 8081.
- **Base de Datos MySQL**: Expuesta en el puerto 3309.

## Requisitos Previos

- Docker
- Docker Compose

## Configuración

Para configurar y ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio:
    ```bash
    git clone <url-del-repositorio>
    cd <directorio-del-repositorio>
    ```

2. Inicia los contenedores Docker:
    ```bash
    docker compose up -d
    ```

## Configuración Adicional

Las configuraciones son las siguientes:

- **Microservicio de Clientes y Personas**: Accesible en `http://localhost:8080`
- **Microservicio de Cuentas y Transacciones**: Accesible en `http://localhost:8081`
- **Base de Datos MySQL**: Accesible en `localhost:3309`, sin contraseña.

### Configuración en Docker Compose

Todas las configuraciones pueden cambiarse en el archivo `docker-compose.yml` ubicado en el directorio raíz del proyecto.

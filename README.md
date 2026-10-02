# 🏛️ PermitFlow API - Sistema de Gestión de Trámites y Licencias

> *"No vengo solo de escribir código, vengo de resolver problemas con metodología. La gestión empresarial me enseñó el 'por qué' del negocio. Ahora domino el 'cómo' con tecnología."*

## 📌 Sobre el Proyecto
**PermitFlow** es una API RESTful empresarial diseñada para digitalizar y optimizar el flujo de trabajo de solicitudes de licencias y expedientes públicos. 

### 💡 ¿Por qué este proyecto?
Durante mi experiencia previa como Gestor de Archivo en la Biblioteca Pública Piloto, gestioné licencias urbanísticas y expedientes. Identifiqué que los flujos de aprobación, los cambios de estado y la trazabilidad eran procesos complejos y propensos a errores manuales. Desarrollé esta API para aplicar una solución tecnológica real a ese problema, garantizando integridad de datos, roles claros y auditoría de estados.

## 🚀 Características Clave
- **Autenticación y Autorización (RBAC):** Spring Security + JWT. Diferenciación estricta de permisos entre roles `CIUDADANO`, `FUNCIONARIO` y `ADMIN`.
- **Máquina de Estados (Workflow):** Validación estricta de reglas de negocio. (Ej. Una solicitud no puede saltar de `RADICADO` a `APROBADO` sin pasar por `EN_REVISION`).
- **Arquitectura Limpia:** Separación estricta de responsabilidades (Controller-Service-Repository) y uso de DTOs para proteger las entidades.
- **Manejo Global de Excepciones:** Respuestas HTTP estandarizadas y limpias (400, 404, 403) en lugar de stack traces.
- **Testing Unitario:** Cobertura de lógica de negocio con **JUnit 5 y Mockito**, validando tanto el camino feliz como los casos borde.
- **Dockerizado:** Levantamiento completo del entorno (App + DB) con `docker-compose` para despliegue inmediato.

## 🛠️ Stack Tecnológico
- **Backend:** Java 21, Spring Boot 3.2
- **Seguridad:** Spring Security, JWT (jjwt), BCrypt
- **Base de Datos:** MySQL 8.0, Spring Data JPA, Hibernate
- **Testing:** JUnit 5, Mockito
- **Herramientas:** Docker, Docker Compose, Maven, Swagger/OpenAPI, Postman

## 🐳 Ejecución con Docker (Recomendado)
La forma más rápida de levantar todo el entorno sin configurar bases de datos locales:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Daniel-Monsalve/permitflow-api.git
   cd permitflow-api

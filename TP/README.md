# Proyecto TPI - Transportes (Scaffold)

Este repositorio contiene un scaffold para el proyecto pedido: simulador backend para una empresa transportista.

Componentes incluidos:
- `orders-service`: microservicio para solicitudes, contenedores y rutas.
- `fleet-service`: microservicio para camiones, depósitos y asignaciones.
- `api-gateway`: gateway (Spring Cloud Gateway) para enrutar y validar tokens.
- `docker-compose.yml`: orquesta Keycloak, Postgres y los servicios (build localmente).
- `postman_collection.json`: colección de ejemplo para pruebas.

Requisitos previos:
- Docker y Docker Compose instalados.
- Java 17 y Maven (para desarrollo local si no usas Docker builds).
- API Key de Google Maps Directions (opcional para pruebas reales). Establecer en las variables de entorno o `application.yml`.

Cómo levantar (rápido):
1. Construir imágenes con maven en cada servicio (o usar `docker-compose build`).
2. Ejecutar: `docker-compose up --build` desde la raíz del proyecto.

Keycloak:
- El `docker-compose.yml` incluye un contenedor Keycloak. Debes importar o crear un `realm` y clientes para las aplicaciones (se provee un `realm-export.json` de ejemplo y pasos en la sección "Keycloak" más abajo).

Notas:
- Este scaffold implementa las entidades y endpoints mínimos solicitados y la integración con Google Directions preparada (necesita API key).
- Swagger/OpenAPI está disponible en cada servicio en `/swagger-ui.html` o `/v3/api-docs`.

Siguientes pasos recomendados:
- Importar el realm en Keycloak o crear los clientes/roles manualmente.
- Probar la colección de Postman y verificar la autenticación JWT (obtener token en Keycloak).

Documentación adicional y endpoints de ejemplo están más abajo en este README.


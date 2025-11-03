# Backend TPI 2025 - Sistema de Gestión Transportista

## Descripción del Proyecto

Sistema completo de microservicios para una empresa transportista que gestiona el traslado de contenedores, implementando el enunciado completo del TPI 2025.

## Arquitectura del Sistema

### Microservicios Implementados

1. **API Gateway** (Puerto 8000)
   - Punto de entrada único al sistema
   - Enrutamiento a todos los microservicios
   - Autenticación JWT centralizada

2. **Solicitudes Service** (Puerto 8081)
   - Gestión de solicitudes de transporte
   - Administración de contenedores
   - Gestión de rutas y tramos

3. **Logística Service** (Puerto 8082)
   - Gestión de depósitos
   - Administración de camiones
   - Control de disponibilidad

4. **Tarifas Service** (Puerto 8083)
   - Gestión de clientes
   - Configuración de tarifas
   - Integración con Google Maps API

5. **Tracking Service** (Puerto 8084)
   - Seguimiento de contenedores en tiempo real
   - Historial de estados

### Infraestructura

- **PostgreSQL** (Puerto 5432): Base de datos única compartida
- **Keycloak** (Puerto 8080): Servidor de autenticación y autorización

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Cloud Gateway
- Spring Security + OAuth2
- Spring Data JPA
- PostgreSQL 15
- Keycloak 24.0.3
- Docker & Docker Compose
- Maven (multi-módulo)
- OpenAPI/Swagger

## Prerequisitos

- Docker Desktop instalado y corriendo
- Docker Compose
- Java 17 (para desarrollo local)
- Maven 3.9+ (para desarrollo local)
- Google Maps API Key (opcional para funcionalidad completa)

## Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone https://github.com/AriGC25/BackendTPI.git
cd BackendTPI
```

### 2. Configurar Google Maps API Key (Opcional)

Para habilitar el cálculo de distancias reales, configure su API Key:

```bash
export GOOGLE_MAPS_API_KEY="your_api_key_here"
```

O edite el archivo `tarifas-service/src/main/resources/application.yml`

### 3. Construir el Proyecto (Opcional - Para desarrollo local)

```bash
mvn clean install
```

### 4. Iniciar el Sistema Completo con Docker Compose

```bash
docker-compose up --build
```

Este comando:
- Construye todas las imágenes Docker
- Inicia PostgreSQL con datos de prueba
- Inicia Keycloak con el realm preconfigurado
- Inicia todos los microservicios

### 5. Verificar que los Servicios Están Corriendo

Espere aproximadamente 2-3 minutos para que todos los servicios inicien completamente.

Verifique el estado:
```bash
docker-compose ps
```

## Acceso a los Servicios

### Endpoints Principales

- **API Gateway**: http://localhost:8000
- **Keycloak Admin Console**: http://localhost:8080/admin
  - Usuario: `admin`
  - Contraseña: `admin`

### Documentación Swagger/OpenAPI

Cada microservicio expone su documentación:

- **Solicitudes Service**: http://localhost:8081/swagger-ui.html
- **Logística Service**: http://localhost:8082/swagger-ui.html
- **Tarifas Service**: http://localhost:8083/swagger-ui.html
- **Tracking Service**: http://localhost:8084/swagger-ui.html

## Autenticación

### Usuarios de Prueba

El sistema incluye tres usuarios preconfigurados en Keycloak:

1. **Cliente**
   - Usuario: `cliente1`
   - Contraseña: `cliente123`
   - Rol: `CLIENTE`

2. **Operador**
   - Usuario: `operador1`
   - Contraseña: `operador123`
   - Rol: `OPERADOR`

3. **Transportista**
   - Usuario: `transportista1`
   - Contraseña: `transportista123`
   - Rol: `TRANSPORTISTA`

### Obtener Token JWT

```bash
curl -X POST "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=backend-client" \
  -d "client_secret=backend-client-secret" \
  -d "grant_type=password" \
  -d "username=operador1" \
  -d "password=operador123"
```

## Ejemplos de Uso

### 1. Crear un Cliente (Como OPERADOR)

```bash
curl -X POST "http://localhost:8000/api/tarifas/clientes" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Test",
    "apellido": "Usuario",
    "dni": "99999999",
    "domicilio": "Calle Test 123",
    "telefono": "+5491199999999",
    "email": "test@example.com"
  }'
```

### 2. Listar Depósitos

```bash
curl -X GET "http://localhost:8000/api/logistica/depositos" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 3. Crear una Solicitud de Transporte (Como CLIENTE)

```bash
curl -X POST "http://localhost:8000/api/solicitudes/solicitudes" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 1,
    "pesoContenedor": 10.5,
    "volumenContenedor": 25.0,
    "descripcionContenedor": "Mercadería general",
    "direccionOrigen": "Av. Corrientes 1234, CABA",
    "latitudOrigen": -34.6037,
    "longitudOrigen": -58.3816,
    "direccionDestino": "San Martín 890, Córdoba",
    "latitudDestino": -31.4201,
    "longitudDestino": -64.1888
  }'
```

## Modelo de Datos

### Entidades Principales

1. **Cliente**: Datos personales y de contacto
2. **Contenedor**: Peso, volumen, estado
3. **Solicitud**: Origen, destino, costos, tiempos
4. **Ruta**: Conjunto de tramos para una solicitud
5. **Tramo**: Segmento de transporte con origen y destino
6. **Camión**: Patente, capacidades, disponibilidad
7. **Depósito**: Ubicación, coordenadas, capacidad
8. **Tarifa**: Configuración de costos por tipo de tramo

## Roles y Permisos

### CLIENTE
- Crear solicitudes de transporte
- Consultar estado de sus solicitudes
- Ver costos y tiempos estimados

### OPERADOR
- CRUD completo de todas las entidades
- Asignar rutas a solicitudes
- Asignar camiones a tramos
- Consultar todas las solicitudes

### TRANSPORTISTA
- Ver tramos asignados
- Actualizar estado de tramos
- Registrar inicio y fin de traslados

## Base de Datos

### Datos de Prueba Incluidos

El sistema incluye datos de prueba:
- 3 Clientes
- 4 Depósitos (Buenos Aires, La Plata, Rosario, Córdoba)
- 5 Camiones con diferentes capacidades
- Tarifas configuradas para todos los tipos de tramos

## Desarrollo Local

### Ejecutar un Servicio Individual

```bash
cd solicitudes-service
mvn spring-boot:run
```

Asegúrese de que PostgreSQL y Keycloak estén corriendo.

### Ejecutar Tests

```bash
mvn test
```

## Troubleshooting

### Los servicios no inician

1. Verifique que Docker Desktop esté corriendo
2. Verifique los logs: `docker-compose logs -f`
3. Reinicie el sistema: `docker-compose down && docker-compose up --build`

### Error de conexión a la base de datos

Espere 30 segundos más para que PostgreSQL inicialice completamente.

### Keycloak no responde

Keycloak puede tardar 1-2 minutos en estar completamente disponible. Verifique:
```bash
docker-compose logs keycloak
```

## Detener el Sistema

```bash
docker-compose down
```

Para eliminar también los volúmenes (datos):
```bash
docker-compose down -v
```

## Estructura del Proyecto

```
BackendTPI/
├── pom.xml                          # Parent POM
├── docker-compose.yml               # Orquestación de servicios
├── README.md                        # Este archivo
├── api-gateway/                     # API Gateway
├── solicitudes-service/             # Microservicio de Solicitudes
├── logistica-service/               # Microservicio de Logística
├── tarifas-service/                 # Microservicio de Tarifas
├── tracking-service/                # Microservicio de Tracking
├── database/                        # Scripts de inicialización
│   └── init-scripts/
│       └── 01-init-data.sql
└── keycloak-config/                 # Configuración de Keycloak
    └── transportista-realm.json
```

## Características Implementadas

✅ Arquitectura de microservicios completa
✅ API Gateway con Spring Cloud Gateway
✅ Autenticación JWT con Keycloak
✅ Control de acceso basado en roles
✅ Base de datos PostgreSQL compartida
✅ Documentación OpenAPI/Swagger
✅ Docker Compose para despliegue
✅ Datos de prueba precargados
✅ Manejo de errores y validaciones
✅ Logging configurado

## Próximas Mejoras

- Implementar Google Maps Directions API
- Agregar cálculo automático de tarifas
- Implementar tracking en tiempo real
- Agregar tests de integración completos
- Implementar circuit breakers
- Agregar métricas con Prometheus/Grafana

## Contacto y Soporte

Para preguntas o problemas, cree un issue en el repositorio de GitHub.

## Licencia

Este proyecto fue desarrollado como parte del TPI 2025.

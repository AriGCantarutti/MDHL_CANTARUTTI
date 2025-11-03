# Resumen de Modificaciones - Sistema de Transporte

## Fecha: 2025-11-02

## Modificaciones Realizadas

### 1. **Tarifas Service** - SecurityConfig.java

**Archivo**: `tarifas-service/src/main/java/com/transportista/tarifas/config/SecurityConfig.java`

**Cambios**:
- **Clientes (POST)**: Permite a OPERADOR y CLIENTE registrar clientes
- **Clientes (GET)**: Permite a OPERADOR y CLIENTE consultar información de clientes
- **Clientes (PUT/DELETE)**: Solo OPERADOR puede modificar o eliminar
- **Tarifas (GET)**: OPERADOR y CLIENTE pueden consultar tarifas
- **Tarifas (POST/PUT/DELETE)**: Solo OPERADOR puede gestionar tarifas
- **Cálculos**: Solo OPERADOR puede acceder a cálculos

**Requerimientos funcionales cubiertos**:
- Registrar cliente si no existe previamente
- Consultar tarifas para calcular costos estimados

---

### 2. **Solicitudes Service** - SecurityConfig.java

**Archivo**: `solicitudes-service/src/main/java/com/transportista/solicitudes/config/SecurityConfig.java`

**Cambios**:
- **Solicitudes (POST)**: CLIENTE y OPERADOR pueden crear solicitudes
- **Solicitudes (GET individual)**: CLIENTE, OPERADOR y TRANSPORTISTA pueden consultar una solicitud específica
- **Solicitudes (GET todas)**: Solo OPERADOR puede listar todas las solicitudes
- **Solicitudes por estado**: Solo OPERADOR puede filtrar por estado
- **Rutas (GET)**: OPERADOR y TRANSPORTISTA pueden consultar rutas
- **Rutas (POST/PUT)**: Solo OPERADOR puede asignar y modificar rutas
- **Tramos (GET)**: OPERADOR y TRANSPORTISTA pueden consultar tramos
- **Tramos (POST)**: Solo OPERADOR puede crear y asignar tramos
- **Tramos (PUT iniciar/finalizar)**: OPERADOR y TRANSPORTISTA pueden iniciar/finalizar tramos
- **Tramos (PUT otros)**: Solo OPERADOR puede modificar otros aspectos

**Requerimientos funcionales cubiertos**:
- Registrar una nueva solicitud de transporte (Cliente)
- Consultar el estado del transporte (Cliente)
- Consultar rutas tentativas (Operador)
- Asignar una ruta con todos sus tramos (Operador)
- Consultar contenedores pendientes con filtros (Operador)
- Asignar camión a un tramo (Operador)
- Determinar el inicio o fin de un tramo (Transportista)

---

### 3. **Tracking Service** - SecurityConfig.java

**Archivo**: `tracking-service/src/main/java/com/transportista/tracking/config/SecurityConfig.java`

**Cambios**:
- **Tracking (GET)**: CLIENTE, OPERADOR y TRANSPORTISTA pueden consultar el tracking
- **Tracking (POST)**: OPERADOR y TRANSPORTISTA pueden registrar eventos de tracking

**Requerimientos funcionales cubiertos**:
- Consultar el estado del transporte de un contenedor (Cliente, Operador, Transportista)

---

### 4. **Logistica Service** - SecurityConfig.java (Ya estaba correcto)

**Archivo**: `logistica-service/src/main/java/com/transportista/logistica/config/SecurityConfig.java`

**Configuración actual**:
- **Depósitos y Camiones (GET)**: OPERADOR y CLIENTE pueden consultar
- **Depósitos y Camiones (POST/PUT/DELETE)**: Solo OPERADOR puede gestionar

**Requerimientos funcionales cubiertos**:
- Registrar y actualizar depósitos, camiones (Operador)
- Validar capacidad máxima de camiones

---

## Cómo Probar Cada Rol

### **ROL: CLIENTE**

**Usuario**: `cliente1`  
**Password**: `cliente123`

#### Obtener Token:
```powershell
$response = Invoke-WebRequest -Uri "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" `
  -Method POST `
  -ContentType "application/x-www-form-urlencoded" `
  -Body @{
    client_id="backend-client"
    client_secret="backend-client-secret"
    grant_type="password"
    username="cliente1"
    password="cliente123"
  }

$token = ($response.Content | ConvertFrom-Json).access_token
$headers = @{ "Authorization" = "Bearer $token" }
```

#### Endpoints que puede usar:

1. **Registrar un cliente** (Método que FUNCIONA):
```powershell
# Obtener token primero
$response = Invoke-WebRequest -Uri "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" `
  -Method POST -ContentType "application/x-www-form-urlencoded" `
  -Body @{
    client_id="backend-client"
    client_secret="backend-client-secret"
    grant_type="password"
    username="cliente1"
    password="cliente123"
  }
$token = ($response.Content | ConvertFrom-Json).access_token
$headers = @{ "Authorization" = "Bearer $token" }

# Crear cliente usando JSON como string (IMPORTANTE: NO usar ConvertTo-Json)
$clienteJson = @'
{
  "nombre": "Cliente",
  "apellido": "Uno",
  "dni": "12345678",
  "domicilio": "Calle 123",
  "telefono": "123456789",
  "email": "cliente1@example.com"
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($clienteJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/tarifas/clientes" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

2. **Crear una solicitud de transporte** (Método que FUNCIONA):
```powershell
$solicitudJson = @'
{
  "clienteId": 1,
  "pesoContenedor": 15000.0,
  "volumenContenedor": 30.0,
  "descripcionContenedor": "Contenedor de mercancias",
  "direccionOrigen": "Av. Libertador 1000, CABA",
  "latitudOrigen": -34.5875,
  "longitudOrigen": -58.4189,
  "direccionDestino": "Av. Corrientes 5000, CABA",
  "latitudDestino": -34.5995,
  "longitudDestino": -58.4320
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($solicitudJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

3. **Consultar el estado de una solicitud**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes/1" `
  -Headers $headers
```

4. **Consultar tracking de un contenedor**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/tracking/tracking/contenedor/1" `
  -Headers $headers
```

5. **Consultar depósitos activos**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/logistica/depositos/activos" `
  -Headers $headers
```

6. **Consultar tarifas**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/tarifas/tarifas" `
  -Headers $headers
```

---

### **ROL: OPERADOR**

**Usuario**: `operador1`  
**Password**: `operador123`

#### Obtener Token:
```powershell
$response = Invoke-WebRequest -Uri "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" `
  -Method POST `
  -ContentType "application/x-www-form-urlencoded" `
  -Body @{
    client_id="backend-client"
    client_secret="backend-client-secret"
    grant_type="password"
    username="operador1"
    password="operador123"
  }

$token = ($response.Content | ConvertFrom-Json).access_token
$headers = @{ "Authorization" = "Bearer $token" }
```

#### Endpoints que puede usar:

1. **Listar todas las solicitudes**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes" `
  -Headers $headers
```

2. **Listar solicitudes por estado**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes/estado/PROGRAMADA" `
  -Headers $headers
```

3. **Crear un depósito** (Método que FUNCIONA):
```powershell
$depositoJson = @'
{
  "nombre": "Deposito Central",
  "ciudadId": 2,
  "capacidadMaxima": 100,
  "costoEstadia": 500.0,
  "activo": true
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($depositoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/logistica/depositos" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

4. **Registrar un camión** (Método que FUNCIONA):
```powershell
$camionJson = @'
{
  "patente": "ABC123",
  "marca": "Mercedes Benz",
  "modelo": "Actros 2023",
  "capacidadPeso": 25000.0,
  "capacidadVolumen": 60.0,
  "disponible": true
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($camionJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/logistica/camiones" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

5. **Crear una tarifa** (Método que FUNCIONA):
```powershell
$tarifaJson = @'
{
  "concepto": "Transporte por KM",
  "valor": 10.5,
  "unidad": "KM",
  "activo": true
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($tarifaJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/tarifas/tarifas" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

6. **Asignar una ruta a una solicitud** (Método que FUNCIONA):
```powershell
$rutaJson = @'
{
  "solicitudId": 1,
  "distanciaTotal": 450.0,
  "tiempoEstimado": 8,
  "costoEstimado": 15000.0
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($rutaJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/rutas" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

7. **Asignar camión a un tramo** (Método que FUNCIONA):
```powershell
$tramoJson = @'
{
  "rutaId": 1,
  "camionId": 1,
  "depositoOrigenId": 1,
  "depositoDestinoId": 2,
  "distancia": 150.0,
  "orden": 1
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($tramoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

---

### **ROL: TRANSPORTISTA**

**Usuario**: `transportista1`  
**Password**: `transportista123`

#### Obtener Token:
```powershell
$response = Invoke-WebRequest -Uri "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" `
  -Method POST `
  -ContentType "application/x-www-form-urlencoded" `
  -Body @{
    client_id="backend-client"
    client_secret="backend-client-secret"
    grant_type="password"
    username="transportista1"
    password="transportista123"
  }

$token = ($response.Content | ConvertFrom-Json).access_token
$headers = @{ "Authorization" = "Bearer $token" }
```

#### Endpoints que puede usar:

1. **Ver tramos asignados** (filtrar por su camión):
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos" `
  -Headers $headers
```

2. **Consultar una solicitud específica**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes/1" `
  -Headers $headers
```

3. **Iniciar un tramo** (Método que FUNCIONA):
```powershell
$iniciarTramoJson = @'
{
  "fechaInicio": "2025-11-03T14:00:00"
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($iniciarTramoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos/1/iniciar" `
  -Method PUT -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

4. **Finalizar un tramo** (Método que FUNCIONA):
```powershell
$finalizarTramoJson = @'
{
  "fechaFin": "2025-11-03T22:00:00"
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($finalizarTramoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos/1/finalizar" `
  -Method PUT -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

5. **Consultar tracking de una solicitud**:
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/tracking/tracking/solicitud/1" `
  -Headers $headers
```

---

## Verificación de los Cambios

Después de reiniciar los servicios con Docker, puedes verificar que los cambios se aplicaron correctamente:

### 1. Verificar que los servicios están corriendo:
```powershell
docker ps
```

### 2. Verificar los logs de un servicio específico:
```powershell
# Para tarifas-service
docker logs transportista-tarifas

# Para solicitudes-service
docker logs transportista-solicitudes

# Para tracking-service
docker logs transportista-tracking
```

### 3. Probar un endpoint público (no debería requerir autenticación):
```powershell
Invoke-WebRequest -Uri "http://localhost:8082/actuator/health"
```

---

## Resolución de Problemas

### Error 401 - No Autorizado
- **Causa**: Token inválido, expirado, o servicios no están corriendo
- **Solución**: 
  1. Verificar que todos los servicios estén corriendo: `docker ps`
  2. Si solo está PostgreSQL, levantar todos los servicios: `docker-compose up -d`
  3. Esperar 30 segundos y obtener un nuevo token
  4. Los tokens expiran cada 5 minutos, obtén uno nuevo si llevas tiempo sin usarlo

### Error 403 - Forbidden
- **Causa**: El usuario no tiene el rol necesario para ese endpoint, O hay errores de validación en el body
- **Solución**: 
  1. Verificar que estás usando el usuario correcto según la tabla de roles
  2. Verificar que el body incluya TODOS los campos obligatorios
  3. Revisar los logs del servicio para ver el error exacto: `docker logs transportista-[servicio] --tail 50`

### Error 404 - Not Found
- **Causa**: La URL del endpoint es incorrecta o el servicio no está levantado
- **Solución**: 
  1. Verificar que el gateway esté correctamente configurado
  2. Verificar que el servicio backend esté corriendo
  3. Revisar los logs del gateway para ver cómo se está enrutando la petición

### Error 400 - Bad Request / JSON parse error
- **Causa**: Problemas de codificación UTF-8 en PowerShell al enviar JSON con `ConvertTo-Json`
- **Solución**: Usar una de estas alternativas:
  
  **Opción 1 - Convertir a UTF-8 sin BOM:**
  ```powershell
  $body = @{ campo = "valor" } | ConvertTo-Json
  $utf8NoBom = New-Object System.Text.UTF8Encoding $false
  $bodyBytes = $utf8NoBom.GetBytes($body)
  
  Invoke-WebRequest -Uri "URL" -Method POST -Headers $headers `
    -ContentType "application/json; charset=utf-8" -Body $bodyBytes
  ```
  
  **Opción 2 - Usar JSON directo como string:**
  ```powershell
  $json = '{ "campo": "valor" }'
  
  Invoke-WebRequest -Uri "URL" -Method POST -Headers $headers `
    -ContentType "application/json; charset=utf-8" `
    -Body ([System.Text.Encoding]::UTF8.GetBytes($json))
  ```
  
  **Importante**: Evita caracteres especiales (tildes, ñ) en los valores o usa la conversión UTF-8 correcta.

---

## Script Completo de Prueba para ROL CLIENTE

**Copia y ejecuta este script completo en PowerShell:**

```powershell
# ===== PASO 1: Verificar que los servicios estén corriendo =====
Write-Host "Verificando servicios..." -ForegroundColor Cyan
docker ps --format "table {{.Names}}\t{{.Status}}"

# ===== PASO 2: Obtener token para CLIENTE =====
Write-Host "`nObteniendo token para cliente1..." -ForegroundColor Cyan
$response = Invoke-WebRequest -Uri "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" `
  -Method POST `
  -ContentType "application/x-www-form-urlencoded" `
  -Body @{
    client_id="backend-client"
    client_secret="backend-client-secret"
    grant_type="password"
    username="cliente1"
    password="cliente123"
  }

$token = ($response.Content | ConvertFrom-Json).access_token
$headers = @{ "Authorization" = "Bearer $token" }
Write-Host "Token obtenido: $($token.Substring(0,20))..." -ForegroundColor Green

# ===== PASO 3: Registrar un cliente (usando JSON directo para evitar problemas de encoding) =====
Write-Host "`nRegistrando cliente..." -ForegroundColor Cyan
$clienteJson = '{
  "nombre": "Cliente",
  "apellido": "Uno",
  "dni": "12345678",
  "domicilio": "Calle 123",
  "telefono": "123456789",
  "email": "cliente1@example.com"
}'

try {
  $result = Invoke-WebRequest -Uri "http://localhost:8000/api/tarifas/clientes" `
    -Method POST `
    -Headers $headers `
    -ContentType "application/json; charset=utf-8" `
    -Body ([System.Text.Encoding]::UTF8.GetBytes($clienteJson))
  
  Write-Host "Cliente creado exitosamente!" -ForegroundColor Green
  $clienteCreado = $result.Content | ConvertFrom-Json
  Write-Host "ID del cliente: $($clienteCreado.id)" -ForegroundColor Yellow
} catch {
  Write-Host "Error al crear cliente: $_" -ForegroundColor Red
  Write-Host "Asumiendo que el cliente ya existe con ID=1" -ForegroundColor Yellow
}

# ===== PASO 4: Crear una solicitud de transporte =====
Write-Host "`nCreando solicitud de transporte..." -ForegroundColor Cyan
$solicitudJson = '{
  "clienteId": 1,
  "pesoContenedor": 15000.0,
  "volumenContenedor": 30.0,
  "descripcionContenedor": "Contenedor de mercancias",
  "direccionOrigen": "Av. Libertador 1000, CABA",
  "latitudOrigen": -34.5875,
  "longitudOrigen": -58.4189,
  "direccionDestino": "Av. Corrientes 5000, CABA",
  "latitudDestino": -34.5995,
  "longitudDestino": -58.4320
}'

try {
  $result = Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes" `
    -Method POST `
    -Headers $headers `
    -ContentType "application/json; charset=utf-8" `
    -Body ([System.Text.Encoding]::UTF8.GetBytes($solicitudJson))
  
  Write-Host "Solicitud creada exitosamente!" -ForegroundColor Green
  $solicitud = $result.Content | ConvertFrom-Json
  Write-Host "Numero de solicitud: $($solicitud.numeroSolicitud)" -ForegroundColor Yellow
  Write-Host "ID: $($solicitud.id)" -ForegroundColor Yellow
  Write-Host "Estado: $($solicitud.estado)" -ForegroundColor Yellow
} catch {
  Write-Host "Error al crear solicitud: $_" -ForegroundColor Red
  $errorDetails = $_.Exception.Response.GetResponseStream()
  $reader = New-Object System.IO.StreamReader($errorDetails)
  $responseBody = $reader.ReadToEnd()
  Write-Host "Detalles del error: $responseBody" -ForegroundColor Red
}

# ===== PASO 5: Consultar solicitudes del cliente =====
Write-Host "`nConsultando solicitudes del cliente..." -ForegroundColor Cyan
try {
  $result = Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes/cliente/1" `
    -Headers $headers
  
  $solicitudes = $result.Content | ConvertFrom-Json
  Write-Host "Solicitudes encontradas: $($solicitudes.Count)" -ForegroundColor Green
  $solicitudes | ForEach-Object {
    Write-Host "  - Solicitud #$($_.numeroSolicitud) - Estado: $($_.estado)" -ForegroundColor Cyan
  }
} catch {
  Write-Host "Error al consultar solicitudes: $_" -ForegroundColor Red
}

Write-Host "`n===== Prueba completada =====" -ForegroundColor Green
```

---

## Comandos Individuales Corregidos (Para copiar/pegar)

### 1. Obtener Token
```powershell
$response = Invoke-WebRequest -Uri "http://localhost:8080/realms/transportista-realm/protocol/openid-connect/token" `
  -Method POST -ContentType "application/x-www-form-urlencoded" `
  -Body @{
    client_id="backend-client"
    client_secret="backend-client-secret"
    grant_type="password"
    username="cliente1"
    password="cliente123"
  }
$token = ($response.Content | ConvertFrom-Json).access_token
$headers = @{ "Authorization" = "Bearer $token" }
```

### 2. Crear Cliente (CORREGIDO con UTF-8)
```powershell
$clienteJson = '{
  "nombre": "Cliente",
  "apellido": "Uno",
  "dni": "12345678",
  "domicilio": "Calle 123",
  "telefono": "123456789",
  "email": "cliente1@example.com"
}'

Invoke-WebRequest -Uri "http://localhost:8000/api/tarifas/clientes" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body ([System.Text.Encoding]::UTF8.GetBytes($clienteJson))
```

### 3. Crear Solicitud (CORREGIDO con UTF-8)
```powershell
$solicitudJson = '{
  "clienteId": 1,
  "pesoContenedor": 15000.0,
  "volumenContenedor": 30.0,
  "descripcionContenedor": "Contenedor de mercancias",
  "direccionOrigen": "Av. Libertador 1000, CABA",
  "latitudOrigen": -34.5875,
  "longitudOrigen": -58.4189,
  "direccionDestino": "Av. Corrientes 5000, CABA",
  "latitudDestino": -34.5995,
  "longitudDestino": -58.4320
}'

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body ([System.Text.Encoding]::UTF8.GetBytes($solicitudJson))
```

### 4. Listar Solicitudes (CORREGIDO con UTF-8 sin BOM)
```powershell
Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/solicitudes" `
  -Headers $headers
```

### 5. Crear Depósito (CORREGIDO con UTF-8 sin BOM)
```powershell
$depositoJson = @'
{
  "nombre": "Deposito Central",
  "ciudadId": 2,
  "capacidadMaxima": 100,
  "costoEstadia": 500.0,
  "activo": true
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($depositoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/logistica/depositos" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

### 6. Registrar Camión (CORREGIDO con UTF-8 sin BOM)
```powershell
$camionJson = @'
{
  "patente": "ABC123",
  "marca": "Mercedes Benz",
  "modelo": "Actros 2023",
  "capacidadPeso": 25000.0,
  "capacidadVolumen": 60.0,
  "disponible": true
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($camionJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/logistica/camiones" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

### 7. Crear Tarifa (CORREGIDO con UTF-8 sin BOM)
```powershell
$tarifaJson = @'
{
  "concepto": "Transporte por KM",
  "valor": 10.5,
  "unidad": "KM",
  "activo": true
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($tarifaJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/tarifas/tarifas" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

### 8. Asignar Ruta a Solicitud (CORREGIDO con UTF-8 sin BOM)
```powershell
$rutaJson = @'
{
  "solicitudId": 1,
  "distanciaTotal": 450.0,
  "tiempoEstimado": 8,
  "costoEstimado": 15000.0
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($rutaJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/rutas" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

### 9. Asignar Camión a Tramo (CORREGIDO con UTF-8 sin BOM)
```powershell
$tramoJson = @'
{
  "rutaId": 1,
  "camionId": 1,
  "depositoOrigenId": 1,
  "depositoDestinoId": 2,
  "distancia": 150.0,
  "orden": 1
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($tramoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos" `
  -Method POST -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

### 10. Iniciar Tramo (CORREGIDO con UTF-8 sin BOM)
```powershell
$iniciarTramoJson = @'
{
  "fechaInicio": "2025-11-03T14:00:00"
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($iniciarTramoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos/1/iniciar" `
  -Method PUT -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

### 11. Finalizar Tramo (CORREGIDO con UTF-8 sin BOM)
```powershell
$finalizarTramoJson = @'
{
  "fechaFin": "2025-11-03T22:00:00"
}
'@

$utf8NoBom = New-Object System.Text.UTF8Encoding $false
$bodyBytes = $utf8NoBom.GetBytes($finalizarTramoJson)

Invoke-WebRequest -Uri "http://localhost:8000/api/solicitudes/tramos/1/finalizar" `
  -Method PUT -Headers $headers `
  -ContentType "application/json; charset=utf-8" `
  -Body $bodyBytes
```

# Quarkus Location Telemetry Service

Microservicio en Quarkus con PostgreSQL listo para desplegar con Docker Compose.

## 🚀 Despliegue con Docker

Para compilar y levantar tanto la base de datos PostgreSQL como la aplicación Quarkus:

```bash
docker compose up -d --build
```

Verificar logs:
```bash
docker compose logs -f location-service
```

---

## 📡 Endpoints de la API

### 1. Registrar Ubicación
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/locations`
* **Payload JSON:**
```json
{
  "deviceId": "iphone-01",
  "latitude": 10.3910,
  "longitude": -75.4794,
  "recordedAt": "2026-08-18T14:15:30"
}
```
*(Si no se envía `recordedAt`, el backend asignará la fecha y hora actual automáticamente).*

### 2. Consultar Ubicaciones por Hora
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/locations/by-hour?deviceId=iphone-01&date=2026-08-18&hour=14`
* **Parámetros Query:**
  * `deviceId` *(opcional)*: Identificador del dispositivo a filtrar.
  * `date` *(obligatorio)*: Fecha en formato `YYYY-MM-DD`.
  * `hour` *(opcional, default 0)*: Hora del día entre `0` y `23`. (Ej. `14` filtra entre las 14:00:00 y las 14:59:59).

---

## 🛑 Detener el servicio

```bash
docker compose down
```

# Instrucciones

## Requerimeintos
- Gradle:   8.10.2+
- OpenJDK:  17.0.13_p11+
- Lombok:   v1.18.36

## Validaciones
Antes de inciar, valida que tu IDE tiene instalado el complemento para soportar Lombok, en caso contrario descargar el instalador desde la página oficial de [Lombok](https://projectlombok.org/) así como las demás dependencias.

## Ejecución
### Limpiar y construir 
```bash
~$gradle clean build
```

### Ejecutar
```bash
~$gradle bootRun
```

## Base de Datos
Para este ejemplo se requiere el uso de PostgreSQL, puedes utilizar docker para obtener una copia funcional:

```bash
~$docker run --name some-postgres -e POSTGRES_PASSWORD=2449 --publish 5432:5432 -d postgres
```

Se ha asigando unpassword de ejemplo que tambien puedes encontrar en el archivo "src/main/resources/application.yml", favor de actualizar la contraseña de ser necesario.


### Datos requeridos
Antes de continuar debes ejecutar una vez la aplicación con el fin de crear las tablas necesarias, a continuacion, utilizando tu IDE de preferencia para SQL, ejecuta lo siguinete:

```sql
# Roles
INSERT INTO authorities ("name") VALUES('administrator');
INSERT INTO authorities ("name") VALUES('client');

# Usuario
INSERT INTO users ("password", username) VALUES('1234567890', 'admin@domain.io');

# Asignación
INSERT INTO user_authorities(user_id, authority_id)
SELECT u.id, a.id FROM users u JOIN authorities a ON a.name = 'administrator' WHERE u.username= 'admin@domain.io';
```

### API

El actual desarrollo cuenta con SwaggerUI, para poder acceder has click [aqui](http://localhost:8080/api/swagger-ui/index.html#/).

Para poder utilizar las funciones expuestas se require un token, puedes obtener uno ejecutando lo siguinete en linea de comando:

```
curl --location 'http://127.0.0.1:8080/api/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "admin@domain.io",
    "password": "1234567890"
}'
```

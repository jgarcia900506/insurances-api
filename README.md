# Instrucciones

## Requerimeintos
- Gradle:   8.10.2+
- OpenJDK:  17.0.13_p11+
- Lombok:   1.18.36
- Docker    27.4.1

## Validaciones
Antes de inciar, valida que tu IDE tiene instalado el complemento para soportar Lombok, en caso contrario descargar el instalador desde la página oficial de [Lombok](https://projectlombok.org/) así como las demás dependencias.

## Ejecución
### Limpiar y construir 
```bash
~$gradle clean build
```

### Ejecutar
Para ejecutar el proyecto completo, se requiere tener Docker, opcionalmente se agrega la opcion para ejecutar solo el backend:

* Back

```bash
~$gradle bootRun
```
Nota: para salir solo preciona "Ctrl + C"


* Ejecusion de proyecto completo

```bash
~$docker compose up --build
```
Nota: si no es la primera vez que se ejecuta omitir el argumento "--build"

* Detener de proyecto completo

```bash
~$docker compose down
```


## Base de Datos
Se ha asigando un password por default a los clientes que puedes encontrar en el archivo "src/main/resources/application.yml", en el
caso del administrador favor de utilizar el password "1234567890".


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

# OperacionFuegoQuasar
OperacionFuegoQuasar app esta desarrollada con Spring y PostgreSQL, la cual está alojada en AWS.

## Install
Se debe contar con una base de datos postgresql en local y luego ejecutar el main se la aplicacion de Spring pasando como argumentos el usuario y la clave de conexión, se requiere java 11 y maven
## Usage

```html
- Servicio para enviar la informacion del satelite:
HTTP POST http://operacionfuegoquasar-env-1.eba-mpqxpyw2.us-east-2.elasticbeanstalk.com/topsecret_split/kenobi
Json body parameter example:
{
    "distance": 500.0,
    "message": ["este", " ", " ", "mensaje", " "]
}

- Servicio para consultar el mensaje y ubicación de la nave
HTTP GET http://operacionfuegoquasar-env-1.eba-mpqxpyw2.us-east-2.elasticbeanstalk.com/topsecret_split/
Json response example:
{
    "position": {
        "x": -265.2438180486199,
        "y": 241.46296047634007
    },
    "message": "este es un mensaje secreto"
}
También se sumintra archivo Postman.json en la raiz del proyecto con distintos casos de pruebas.
```
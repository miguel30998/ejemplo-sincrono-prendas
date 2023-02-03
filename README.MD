## Solución ejemplo microservicios monolito
Este es un ejemplo de un monolito con dos microservicios:
- Microservicio clothing: encargado de gestionar la ropa y su stock
- Microservicio users: encargado de gestionar los usuarios
## Diagrama solución despliegue
![](https://github.com/alvruiz/ejemplo-sincrono-prendas/blob/main/images/despliegue.png)
## Peticiones disponibles
[Swagger Clothing](http://localhost:8081/swagger-ui.html#/)
![](https://github.com/alvruiz/ejemplo-sincrono-prendas/blob/main/images/swaggerClothing.png)

[Swagger Users](http://localhost:8080/swagger-ui.html#/)
![](https://github.com/alvruiz/ejemplo-sincrono-prendas/blob/main/images/swaggerUsers.png)

## Eureka
Con eureka se pueden ver los microservicios que están siendo desplegados  
[Eureka](http://localhost:8761/)
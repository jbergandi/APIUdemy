Feature: Ejemplo de Request para Udemy


Scenario: Prueba GET al endpoint de GitHub.
Given el usuario que envia un GET request a https://api.github.com URI
Then el usuario obtiene un 200 status code desde el endPoint: /users/TheFreeRangeTester/repos


Scenario: Validar la cantidad de entradas en la respuesta
Given el usuario que envia un GET request a https://jsonplaceholder.typicode.com URI
Then el usuario valida que obtiene 10 items en el endPoint: /users

@API
Scenario: Validar que un elemento esta en la respuesta
Given el usuario que envia un GET request a https://jsonplaceholder.typicode.com URI
Then el usuario valida que el nombre Bret se encuentra en la respuesta del endPoint: /users
# Swagger

Swagger JSON (generated)    

    http://localhost:8080/v2/api-docs

Swagger UI

    http://localhost:8080/swagger-ui.html


[Swagger JSON](swagger.json)

[openapi JSON](openapi.json)

[openapi YAML](openapi.yaml)

# curl commands

## GET

Get all customers.

    curl http://localhost:8080/customer/

Get specified customers.

    curl http://localhost:8080/customer/123
    curl http://localhost:8080/customer/2112

## PUT 

Create new Customer with Specified Ids.

    curl -X PUT -H "Content-Type: application/json" -d '{"first":"BOB","last":"ROBERTS","dob":"1960-02-01"}' http://localhost:8080/customer/bob

Update existing Customer with Specified Id.

    curl -X PUT -H "Content-Type: application/json" -d '{"first":"SAM","last":"FIREMAN","dob":"1960-02-01"}' http://localhost:8080/customer/sam

## DELETE

    curl -X DELETE http://localhost:8080/customer/123

## PATCH

Update specified fields of specified customer

    curl -X PATCH -H "Content-Type: application/json" -d '{"first":"SAMUEL"}' http://localhost:8080/customer/123




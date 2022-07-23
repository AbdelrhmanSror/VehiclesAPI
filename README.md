# Build the Backend System for a Car Website-VehiclesAPI-Project

Project repository implementing a Vehicles API using Java and Spring Boot that can communicate with separate location and pricing services.

## Features
- REST API exploring the main HTTP verbs and features
- Custom API Error handling using ControllerAdvice
- springdoc-openapi documentation
- HTTP WebClient
- MVC Test
- Automatic model mapping
## Instructions

Check each component to see its details and instructions. Note that all three applications
should be running at once for full operation.

- [Vehicles API](vehicles-api/README.md)
- [Pricing Service](pricing-service/README.md)
- [Map-Service](map-service/README.md)

## Dependencies

The project requires the use of Maven and Spring Boot, along with Java v11.

## Operations

Swagger UI: http://localhost:8761/swagger-ui/index.html

### Create a Vehicle

`POST` `/cars`
```json
{
   "condition":"USED",
   "details":{
      "body":"sedan",
      "model":"Impala",
      "manufacturer":{
         "code":101,
         "name":"Chevrolet"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"3.6L V6",
      "mileage":32280,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"white"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242
   }
}
```

### Retrieve a Vehicle

`GET` `/cars/{id}`

This feature retrieves the Vehicle data from the database
and access the Pricing Service and Boogle Maps to enrich 
the Vehicle information to be presented

### Update a Vehicle

`PUT` `/cars/{id}`

```json
{
   "condition":"USED",
   "details":{
      "body":"sedan",
      "model":"Impala",
      "manufacturer":{
         "code":101,
         "name":"Chevrolet"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"3.6L V6",
      "mileage":32280,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"white"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242
   }
}
```

### Delete a Vehicle

`DELETE` `/cars/{id}`

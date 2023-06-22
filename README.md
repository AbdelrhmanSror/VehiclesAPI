# Backend System for a Car Website-VehiclesAPI-Project

This project repository implements a Vehicles API using Java and Spring Boot that communicates with separate location and pricing services. The project is designed to provide REST API capabilities that explore the main HTTP verbs and features. It also includes Eureka server, Redis Cache in Memory, MySQL relational database, Docker container, custom API error handling using ControllerAdvice, springdoc-openapi documentation, HTTP WebClient, MVC Test, and Automatic model mapping.

## Features

- REST API exploring the main HTTP verbs and features
- Eureka Server.
- Redis Cache in Memory
- MySql relational Database
- Docker container.
- Custom API Error handling using ControllerAdvice
- springdoc-openapi documentation
- HTTP WebClient
- MVC Test
- Automatic model mapping

## Instructions

To run the project, you can choose to run it either as java applications or as docker containers. Note that all three applications should be running simultaneously for full operation.

Check each component to see its details and instructions. Below are the steps to run the project:

### Running as Java Applications

To run the project as a java application or jar files, you must have Mysql server local instance and Redis installed on your device.

1. Go to the root directory of each component and uncomment the necessary configurations, such as the database URL and Eureka client service URL.
2. Comment out the unnecessary configurations.
3. Run each component as a java application or jar file.

### Running as Docker Containers

To run the project as docker containers, you should have Docker installed on your device. 

1. Navigate to the root directory of the project.
2. Run the docker compose file using the command: `docker-compose -f dockercompose.yaml up`.

## Dependencies

The project requires the use of Maven and Spring Boot, along with Java v11.

## Components

### Vehicles API

This component launches the Vehicles API as a Spring Boot application. Additionally, it initializes a few car manufacturers to place in the database, as well as creating the web clients to connect to the Maps and Pricing services.

### Pricing Service

This component handles the format of a GET request to the pricing-service WebClient to get pricing data.

### Map-Service

This component handles the format of a GET request to the map-service WebClient to get location data.

### API Error

This component declares a few quick methods to return errors and other messages from the Vehicles API.

### CarController

This component is the actual REST controller for the application. It implements what happens when GET, POST, PUT, and DELETE requests are received (using methods in the CarService).

### ErrorController

This component helps to handle any invalid arguments fed to the API.

### Address

This component declares a class for use with the MapsClient also to use it with Redis in-memory cache.

### Price

This component declares a class for use with the PriceClient also to use it with Redis in-memory cache.

### MapsClient

This component handles the format of a GET request to the map-service WebClient to get location data. Each time there is a call to map-service, it first checks if the address already stored in memory. If it does not exist, it makes a REST call to map-service.

### PriceClient

This component handles the format of a GET request to the pricing-service WebClient to get pricing data. Each time there is a call to price-service, it first checks if the price already stored in memory. If it does not exist, it makes a REST call to the pricing-service.

### Condition

This component enumerates the available values for the condition of a car (New or Used).

### Location

This component declares information about the location of a vehicle. This is not the exact same as the Address class used by the map-service - its primary use is related to the storage of latitude and longitude values. Because the data, such as address, gathered from map-service is annotated as @Transient, this data is not stored until the next time map-service is called.

### Details

This component declares additional vehicle details, primarily about the car build itself, such as fuel type and mileage.

### Manufacturer

This component declares the Manufacturer class, primarily made of an ID code and the name of the manufacturer.

### Car

This component declares certain information about a given vehicle, mostly that more about the car entry itself (such as CreatedAt). Note that a separate class, Details, also stores additional details about the car that is more specific to things like make, color, and model. Note that similar to Location with data like address, this uses a @Transient tag with price, meaning the Pricing Service must be called each time a price is desired.

### CarRepository

This component provides a type of data persistence while the web service runs.

### ManufacturerRepository

This component provides a type of data persistence while the web service runs, primarily to store manufacturer information like that initialized in VehiclesApiApplication.

### MapCacheRepository

This component provides CRUD operation to store address in memory.

### PriceCacheRepository

This component provides CRUD operation to store pricein memory.

### Eureka Server

This component is a service registry for the microservices that are part of the application. It helps to manage the registration, discovery, and monitoring of microservices.

### Redis Cache

This in-memory cache is used by the application to store frequently accessed data, reducing the number of calls made to external services. The data stored is address and price data, which is retrieved from the map-service and pricing-service, respectively.

### MySQL Database

This component is a relational database used to store data, such as manufacturer and car information.

### HTTP WebClient

This component is used to make HTTP requests to external services, such as the map-service and pricing-service. It is used to retrieve data necessary for the Vehicles API component.

### MVC Test

This component is used to test the REST API endpoints and functionality of the application.

### Automatic Model Mapping

This component is used to map the data between the domain model and the DTO (Data Transfer Object) model. It uses the ModelMapper library to automatically map the data between the two models, reducing the amount of boilerplate code needed.

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

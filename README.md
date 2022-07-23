# Build the Backend System for a Car Website-VehiclesAPI-Project

Project repository implementing a Vehicles API using Java and Spring Boot that can communicate with separate location and pricing services.

## Features
- REST API exploring the main HTTP verbs and features
- Redis Cache in Memory
- MySql relational Database
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

## vehicles
### VehiclesApiApplication
This launches the Vehicles API as a Spring Boot application. Additionally, it initializes a few car manufacturers to place in the databse, as well as creating the web clients to connect to the Maps and Pricing services.

## vehicles.api
### API Error
Declares a few quick methods to return errors and other messages from the Vehicles API.

## CarController
This is our actual REST controller for the application. This implements what happens when GET, POST, PUT and DELETE requests are received (using methods in the CarService).

## ErrorController
This helps to handle any invalid arguments fed to the API.

## vehicles.redis.model
### Address
this declares a class for use with the MapsClient also to use it with redis in memory cache .

### Price
this declares a class for use with the PriceClient also to use it with redis in memory cache.

## vehicles.sql.client.maps
### MapsClient
Handles the format of a GET request to the map-service WebClient to get location data. each time there is a call to map-service it first checks if the address already stored in memory ,go and get it otherwise it makes a rest call to map-service.

## vehicles.sql.client.prices
### PriceClient
Handles the format of a GET request to the pricing-service WebClient to get pricing data.each time there is a call to price-service it first checks if the price already stored in memory ,go and get it otherwise it makes a rest call to map-service.

## vehicles.sql.domain
### Condition
This enumerates the available values for the condition of a car (New or Used).

### Location
This declares information about the location of a vehicle. This is not the exact same as the Address class used by map-service - it's primary use is related to the storage of latitude and longitude values. Because the data, such as address, gathered from map-service is annotated as @Transient, this data is not stored until the next time map-service is called.

### Details
Declares additional vehicle details, primarily about the car build itself, such as fuelType and mileage.

### Manufacturer
This declares the Manufacturer class, primarily just made of a ID code and name of manufacturer.

### Car
This declares certain information about a given vehicle, mostly that more about the car entry itself (such as CreatedAt). Note that a separate class, Details, also stores additional details about the car that is more specific to things like make, color and model. Note that similar to Location with data like address, this uses a @Transient tag with price, meaning the Pricing Service must be called each time a price is desired.


## CarRepository
This repository provide a type of data persistence while the web service runs.


## ManufacturerRepository
This repository provide a type of data persistence while the web service runs, primarily to store manufacturer information like that initialized in VehiclesApiApplication.

## vehicles.redis.repository
### MapCacheRepository
This repository provide crud operation to store address in memory.

### PriceCacheRepository
This repository provide crud operation to store price in memory.

## vehicles.service.domain
CarNotFoundException
This creates a CarNotFoundException that can be thrown when an issue arises in the CarService.

## CarService
The Car Service does a lot of the legwork of the code. It can gather either the entire list of vehicles or just a single vehicle by ID (including calls to the maps and pricing web clients). It can also save updated vehicle information. Lastly, it can delete an existing car. All of these are called by the CarController based on queries to the REST API.



test/../vehicles.api
CarIntegrationTest
Here, the various methods performed by the CarController are performed by creating mock calls to the Vehicles API.




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

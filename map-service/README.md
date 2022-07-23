# Maps Service

This is a Mock that simulates a Maps WebService where, given a latitude
longitude, will return a random address.

## Instructions

Via shell it can be started using

```
$ mvn clean package
```

```
$ java -jar target/map-service-0.0.1-SNAPSHOT.jar
```

The service is available by default on port `9191`. You can check it on the 
command line by using

```
$ curl http://localhost:9191/maps\?lat\=20.0\&lon\=30.0
``` 

You can also import it as a Maven project on your preferred IDE and 
run the class `MapsApplication`.

## Address
This declares the Address entity class , primarily just made of the private variables address, city, state and zip. Note that the latitude and longitude are not stored here - they come from the Vehicles API. It represents an entity in the database that stores the data of address in MySql database.

## MapsApplication
This launches Maps service as a Spring Boot application.

## MapsController
This is our actual REST controller for the application. This implements what a GET request will respond with - in our case, since it is a  WebService, we are just responding with a an address from the AddressService.

## AddressRepository
Repository provides CRUD operation through MySql database.
